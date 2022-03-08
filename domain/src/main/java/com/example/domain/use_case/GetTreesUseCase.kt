package com.example.domain.use_case

import com.example.domain.entities.TreeEntity
import com.example.data.data_source.repository.TreesRepository
import com.example.domain.mappers.mapToEntity
import com.example.domain.util.Resource
import java.lang.Exception

class GetTreesUseCase(
    private val repository: TreesRepository
) {

    suspend operator fun invoke(): Resource<List<TreeEntity>> {
        return try {
            val response = repository.getTreesList().map { it.mapToEntity() }
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Invalid response")
        }
    }
}