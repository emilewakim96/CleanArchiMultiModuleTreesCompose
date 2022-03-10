package com.example.domain.use_case

import com.example.domain.entities.TreeEntity
import com.example.data.data_source.repository.TreesRepository
import com.example.domain.errorHandler.getError
import com.example.domain.fetchstrategy.FetchStrategy
import com.example.domain.mappers.mapToEntity
import com.example.domain.util.Resource

class GetTreesUseCase(
    private val repository: TreesRepository
) {

    suspend operator fun invoke(fetchStrategy: FetchStrategy): Resource<List<TreeEntity>?> {
        return try {
            val response = when (fetchStrategy) {
                FetchStrategy.Remote -> repository.getTreesList(forceRefresh = true)?.map { it.mapToEntity() }
                FetchStrategy.Local -> repository.getTreesListFromDB()?.map { it.mapToEntity() }
                FetchStrategy.Cache -> repository.getTreesList(forceRefresh = false)?.map { it.mapToEntity() }
            }
            Resource.Success(response)
        } catch (t: Throwable) {
            Resource.Error(t.getError())
        }
    }
}