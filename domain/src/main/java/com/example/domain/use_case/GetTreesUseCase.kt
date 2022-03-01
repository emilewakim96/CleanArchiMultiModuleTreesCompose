package com.example.domain.use_case

import com.example.domain.models.Tree
import com.example.domain.repository.TreesRepository
import com.example.domain.util.CachePolicy
import com.example.domain.util.Resource

class GetTreesUseCase(
    private val repository: TreesRepository
) {

    suspend operator fun invoke(): Resource<List<Tree>> {
        return repository.getTreesList()
    }
}