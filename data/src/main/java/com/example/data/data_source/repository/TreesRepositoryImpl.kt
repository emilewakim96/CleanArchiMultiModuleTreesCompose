package com.example.data.data_source.repository

import com.example.common.managers.ConnectionManager
import com.example.data.di.qualifier.RemoteData
import com.example.data.data_source.local.TreesLocalDataSource
import com.example.data.data_source.remote.TreesRemoteDataSource
import com.example.data.data_source.remote.responses.Tree
import com.example.data.di.qualifier.LocalData
import javax.inject.Inject

class TreesRepositoryImpl @Inject constructor(
    @LocalData private val localDataSource: TreesLocalDataSource,
    @RemoteData private val remoteDataSource: TreesRemoteDataSource,
    private val connectionManager: ConnectionManager
): TreesRepository {

    private var cachedTrees: List<Tree>? = listOf()
    private var cachedTreesIsDirty = false

    override suspend fun getTreesList(): List<Tree>? {
        cachedTreesIsDirty = !connectionManager.offline

        if (!cachedTrees.isNullOrEmpty() && !cachedTreesIsDirty) {
            return cachedTrees
        }
        return if (cachedTreesIsDirty)
            getAndSaveRemoteTrees()
        else {
            getAndCacheLocalTrees()
        }
    }

    override suspend fun saveTreeInDB(tree: Tree) {
        localDataSource.saveTree(tree)
    }

    private suspend fun getAndSaveRemoteTrees(): List<Tree>? {
        return remoteDataSource.getTreesList().also { trees ->
                trees?.forEach { tree ->
                    saveTreeInDB(tree)
                }
                cachedTrees = trees
                cachedTreesIsDirty = false
            }
    }

    override suspend fun deleteTreeFromDB(tree: Tree) {
        localDataSource.deleteTree(tree)
    }

    private suspend fun getAndCacheLocalTrees(): List<Tree> {
        return localDataSource.getTreesList().also {
            cachedTrees = it
        }
    }
}