package com.example.domain.use_case

import com.example.common.entity.Tree
import com.example.domain.repository.TreesRepository
import com.example.domain.util.Resource

class GetTreesUseCase(
    private val repository: TreesRepository
) {

    suspend operator fun invoke(): Resource<List<Tree>> {
        return repository.getTreesList()
    }
}