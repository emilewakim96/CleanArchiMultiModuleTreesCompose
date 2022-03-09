package com.example.domain.use_case

import com.example.data.data_source.repository.TreesRepository
import com.example.domain.entities.TreeEntity
import com.example.domain.errorHandler.getError
import com.example.domain.mappers.mapToModel
import com.example.domain.util.Resource

class AddTreeUseCase(
    private val repository: TreesRepository
) {

    suspend operator fun invoke(tree: TreeEntity): Resource<Boolean> {
        return try {
            repository.saveTreeInDB(tree.mapToModel())
            Resource.Success(true)
        } catch (t: Throwable) {
            Resource.Error(t.getError())
        }
    }
}