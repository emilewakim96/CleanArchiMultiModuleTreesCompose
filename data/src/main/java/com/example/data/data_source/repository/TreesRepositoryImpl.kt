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

    private var cachedTrees: List<TreeEntity> = listOf()
    private var cachedTreesIsDirty = false

    override suspend fun getTreesList(): Resource<List<TreeEntity>> {
        cachedTreesIsDirty = !connectionManager.offline

        if (cachedTrees.isNotEmpty() && !cachedTreesIsDirty) {
            return Resource.Success(cachedTrees)
        }
        val remoteTrees = getAndSaveRemoteTrees()
        return if (cachedTreesIsDirty)
            remoteTrees
        else {
            getAndCacheLocalTrees()
        }
    }

    override suspend fun saveTree(tree: TreeEntity) {
        localDataSource.saveTree(tree)
    }

    private suspend fun getAndSaveRemoteTrees(): Resource<List<TreeEntity>> {
        val response = try {
            remoteDataSource.getTreesList().also {
                it.data?.let { trees ->
                    trees.forEach { tree ->
                        saveTree(tree)
                    }
                    cachedTrees = trees
                    cachedTreesIsDirty = false
                }
            }
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return response
    }

    private suspend fun getAndCacheLocalTrees(): Resource<List<TreeEntity>> {
        return localDataSource.getTreesList().also {
            cachedTrees = it.data ?: listOf()
        }
    }
}