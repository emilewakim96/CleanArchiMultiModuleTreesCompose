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
                    import com.example.domain.util.CachePolicy
import javax.inject.Inject

class TreesRepositoryImpl @Inject constructor(
    @LocalData private val localDataSource: TreesLocalDataSource,
    @RemoteData private val remoteDataSource: TreesRemoteDataSource,
    private val context: Context
): TreesRepository(localDataSource, remoteDataSource) {

    private var cachePolicy: CachePolicy.Type? = null

    override suspend fun getTreesList(): Resource<List<Tree>> {
        cachePolicy = if (NetworkUtils.isInternetAvailable(context)) {
            CachePolicy.Type.REFRESH
        } else {
            CachePolicy.Type.ALWAYS
        }
        return fetch(cachePolicy = CachePolicy(type = cachePolicy)) ?: Resource.Success(emptyList())
    }
}