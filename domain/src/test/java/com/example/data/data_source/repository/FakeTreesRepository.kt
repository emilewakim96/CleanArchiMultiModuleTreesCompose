package com.example.data.data_source.repository

import com.example.domain.entities.TreeEntity
import com.example.domain.repository.TreesRepository

class FakeTreesRepository: TreesRepository {

    private val trees = mutableListOf<TreeEntity>()

    override suspend fun getTreesList(): List<TreeEntity>? {
        return trees
    }

    override suspend fun saveTree(tree: TreeEntity) {
        trees.add(tree)
    }

}