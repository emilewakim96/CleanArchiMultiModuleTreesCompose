package com.example.data.data_source.repository

import com.example.data.di.qualifier.RemoteData
import com.example.data.data_source.local.TreesLocalDataSource
import com.example.data.data_source.remote.TreesRemoteDataSource
import com.example.data.data_source.remote.responses.Tree
import com.example.data.di.qualifier.LocalData
import javax.inject.Inject

class TreesRepositoryImpl @Inject constructor(
    @LocalData private val localDataSource: TreesLocalDataSource,
    @RemoteData private val remoteDataSource: TreesRemoteDataSource,
    private val isOffline: Boolean
): TreesRepository {

    private var cachedTrees: List<Tree> = listOf()
    private var cachedTreesIsDirty = false

    override suspend fun getTreesList(): List<Tree> {
        cachedTreesIsDirty = !isOffline

        if (cachedTrees.isNotEmpty() && !cachedTreesIsDirty) {
            return cachedTrees
        }
        return if (cachedTreesIsDirty)
            getAndSaveRemoteTrees()
        else {
            getAndCacheLocalTrees()
        }
    }

    override suspend fun saveTree(tree: Tree) {
        localDataSource.saveTree(tree)
    }

    private suspend fun getAndSaveRemoteTrees(): List<Tree> {
        val response = try {
            remoteDataSource.getTreesList().also { trees ->
                trees.forEach { tree ->
                    saveTree(tree)
                }
                cachedTrees = trees
                cachedTreesIsDirty = false
            }
        } catch (e: Exception) {
            throw Throwable(e.message)
        }
        return response
    }

    private suspend fun getAndCacheLocalTrees(): List<Tree> {
        return localDataSource.getTreesList().also {
            cachedTrees = it
        }
    }
}