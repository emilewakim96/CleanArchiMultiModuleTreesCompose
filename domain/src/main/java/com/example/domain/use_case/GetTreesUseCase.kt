package com.example.domain.use_case

import com.example.domain.entities.TreeEntity
import com.example.domain.repository.TreesRepository
import com.example.domain.util.Resource

class GetTreesUseCase(
    private val repository: TreesRepository
) {

    suspend operator fun invoke(): Resource<List<TreeEntity>> {
        return repository.getTreesList()
    }
}