package com.example.data.data_source.repository

import com.example.data.di.qualifier.RemoteData
import com.example.data.data_source.local.TreesLocalDataSource
import com.example.data.data_source.manager.ConnectionManager
import com.example.data.data_source.remote.TreesRemoteDataSource
import com.example.domain.util.Resource
import com.example.data.di.qualifier.LocalData
import com.example.domain.entities.TreeEntity
import com.example.domain.repository.TreesRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TreesRepositoryImpl @Inject constructor(
    @LocalData private val localDataSource: TreesLocalDataSource,
    @RemoteData private val remoteDataSource: TreesRemoteDataSource,
    private val connectionManager: ConnectionManager
): TreesRepository {

    private var cachedTrees: List<TreeEntity>? = listOf()
    private var cachedTreesIsDirty = false

    override suspend fun getTreesList(): Flow<List<TreeEntity>?> {
        cachedTreesIsDirty = !connectionManager.offline

        if (!cachedTrees.isNullOrEmpty() && !cachedTreesIsDirty) {
            return flow {
                emit(cachedTrees)
            }
        }
        return if (cachedTreesIsDirty) {
            getAndSaveRemoteTrees()
        } else {
            getAndCacheLocalTrees()
        }
    }

    override suspend fun saveTree(tree: TreeEntity) {
        localDataSource.saveTree(tree)
    }

    private suspend fun getAndSaveRemoteTrees(): Flow<List<TreeEntity>?> {
        return remoteDataSource.getTreesList().also { flow ->
            flow.onEach { trees ->
                trees?.forEach { tree ->
                    saveTree(tree)
                }
                cachedTrees = trees
                cachedTreesIsDirty = false
            }
        }
    }

    private suspend fun getAndCacheLocalTrees(): Flow<List<TreeEntity>?> {
        return localDataSource.getTreesList().also {
            cachedTrees = it.first()
        }
    }

    override suspend fun deleteTree(tree: TreeEntity) {
        localDataSource.deleteTree(tree)
    }
}