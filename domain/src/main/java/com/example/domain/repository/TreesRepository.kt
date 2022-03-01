package com.example.domain.repository

import com.example.domain.util.Resource
import com.example.domain.models.Tree

abstract class TreesRepository(
    localDataSource: LocalDataSource<CacheEntry<Resource<List<Tree>>>>,
    remoteDataSource: RemoteDataSource<Resource<List<Tree>>>
) : CachePolicyRepository<Resource<List<Tree>>>(localDataSource, remoteDataSource) {

    abstract suspend fun getTreesList(): Resource<List<Tree>>
}