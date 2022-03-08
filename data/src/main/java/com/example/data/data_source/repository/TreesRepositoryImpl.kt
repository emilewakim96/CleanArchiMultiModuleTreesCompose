package com.example.data.data_source.repository

import com.example.data.di.qualifier.RemoteData
import com.example.data.data_source.local.TreesLocalDataSource
import com.example.data.data_source.manager.ConnectionManager
import com.example.data.data_source.remote.TreesRemoteDataSource
import com.example.domain.util.Resource
import com.example.data.di.qualifier.LocalData
import com.example.domain.entities.TreeEntity
import com.example.domain.repository.TreesRepository
import javax.inject.Inject

class TreesRepositoryImpl @Inject constructor(
    @LocalData private val localDataSource: TreesLocalDataSource,
    @RemoteData private val remoteDataSource: TreesRemoteDataSource,
    private val connectionManager: ConnectionManager
): TreesRepository {

    private var cachedTrees: List<TreeEntity>? = listOf()
    private var cachedTreesIsDirty = false

    override suspend fun getTreesList(): List<TreeEntity>? {
        cachedTreesIsDirty = !connectionManager.offline

        if (!cachedTrees.isNullOrEmpty() && !cachedTreesIsDirty) {
            return cachedTrees
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

    private suspend fun getAndSaveRemoteTrees(): List<TreeEntity>? {
        return remoteDataSource.getTreesList().also { trees ->
            trees?.forEach { tree ->
                saveTree(tree)
            }
            cachedTrees = trees
            cachedTreesIsDirty = false
        }
    }

    private suspend fun getAndCacheLocalTrees(): List<TreeEntity> {
        return localDataSource.getTreesList().also {
            cachedTrees = it
        }
    }
}