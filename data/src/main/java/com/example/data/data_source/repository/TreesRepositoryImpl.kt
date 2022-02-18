package com.example.data.data_source.repository

import android.content.Context
import com.example.data.di.qualifier.RemoteData
import com.example.data.data_source.local.TreesLocalDataSource
import com.example.data.data_source.remote.TreesRemoteDataSource
import com.example.data.data_source.util.NetworkUtils
import com.example.domain.util.Resource
import com.example.data.di.qualifier.LocalData
import com.example.domain.models.Tree
import com.example.domain.repository.TreesRepository
import javax.inject.Inject

class TreesRepositoryImpl @Inject constructor(
    @LocalData private val localDataSource: TreesLocalDataSource,
    @RemoteData private val remoteDataSource: TreesRemoteDataSource,
    private val context: Context
): TreesRepository {

    private var cachedTrees: List<Tree> = listOf()
    private var cachedTreesIsDirty = false

    override suspend fun getTreesList(): Resource<List<Tree>> {
        cachedTreesIsDirty = NetworkUtils.thereIsConnection(context)
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

    override suspend fun saveTree(tree: Tree) {
        localDataSource.insertTree(tree)
    }

    private suspend fun getAndSaveRemoteTrees(): Resource<List<Tree>> {
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

    private suspend fun getAndCacheLocalTrees(): Resource<List<Tree>> {
        return localDataSource.getTreesList().also {
            cachedTrees = it.data ?: listOf()
        }
    }
}