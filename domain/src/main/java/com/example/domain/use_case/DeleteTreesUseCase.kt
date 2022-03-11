package com.example.domain.use_case

import com.example.domain.entities.TreeEntity
import com.example.domain.errorhandler.ErrorHandler
import com.example.domain.repository.TreesRepository
import com.example.domain.util.Resource

class DeleteTreesUseCase(
    private val repository: TreesRepository
) {

    suspend operator fun invoke(tree: TreeEntity): Resource<Boolean> {
        return try {
            repository.deleteTree(tree)
            Resource.Success(true)
        } catch (t: Throwable) {
            Resource.Error(ErrorHandler.getError(t))
        }
    }
}