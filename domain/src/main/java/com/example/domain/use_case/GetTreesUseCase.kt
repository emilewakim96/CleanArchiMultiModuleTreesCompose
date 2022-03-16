package com.example.domain.use_case

import com.example.domain.entities.TreeEntity
import com.example.domain.errorhandler.ErrorHandler
import com.example.domain.repository.TreesRepository
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetTreesUseCase(
    private val repository: TreesRepository
) {

    suspend operator fun invoke(): Flow<Resource<List<TreeEntity>?>> {
        return try {
            val response = repository.getTreesList().first()
             flow {
                emit(Resource.Success(response))
            }
        } catch (t: Throwable) {
            flow {
                emit(Resource.Error(ErrorHandler.getError(t)))
            }
        }
    }
}