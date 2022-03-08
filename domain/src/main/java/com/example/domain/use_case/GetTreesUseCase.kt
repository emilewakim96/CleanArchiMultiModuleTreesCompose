package com.example.domain.use_case

import com.example.domain.entities.TreeEntity
import com.example.domain.repository.TreesRepository
import com.example.domain.util.Resource
import java.lang.Exception

class GetTreesUseCase(
    private val repository: TreesRepository
) {

    suspend operator fun invoke(): Resource<List<TreeEntity>> {
        return try {
            val response = repository.getTreesList()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Invalid response")
        }
    }
}