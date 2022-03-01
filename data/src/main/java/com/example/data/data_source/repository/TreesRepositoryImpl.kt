package com.example.data.data_source.repository

import com.example.data.data_source.local.TreesLocalDataSource
import com.example.data.data_source.manager.ConnectionManager
import com.example.data.data_source.remote.TreesRemoteDataSource
import com.example.data.di.qualifier.LocalData
import com.example.data.di.qualifier.RemoteData
import com.example.domain.models.Tree
import com.example.domain.repository.TreesRepository
import com.example.domain.util.CachePolicy
import com.example.domain.util.Resource
import javax.inject.Inject

class TreesRepositoryImpl @Inject constructor(
    @LocalData private val localDataSource: TreesLocalDataSource,
    @RemoteData private val remoteDataSource: TreesRemoteDataSource,
    private val connectionManager: ConnectionManager
): TreesRepository(localDataSource, remoteDataSource) {

    private var cachePolicy: CachePolicy.Type? = null

    override suspend fun getTreesList(): Resource<List<Tree>> {
        cachePolicy = if (!connectionManager.offline) {
            CachePolicy.Type.REFRESH
        } else {
            CachePolicy.Type.ALWAYS
        }
        return fetch(cachePolicy = CachePolicy(type = cachePolicy)) ?: Resource.Success(emptyList())
    }
}