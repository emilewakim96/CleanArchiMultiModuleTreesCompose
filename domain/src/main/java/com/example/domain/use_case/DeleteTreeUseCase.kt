package com.example.domain.use_case

import com.example.data.data_source.repository.TreesRepository
import com.example.domain.entities.TreeEntity
import com.example.domain.mappers.mapToModel

class DeleteTreeUseCase(
    private val repository: TreesRepository
) {

    suspend operator fun invoke(tree: TreeEntity) {
        return try {
            repository.deleteTreeFromDB(tree.mapToModel())
        } catch (t: Throwable) {
            throw t
        }
    }
}