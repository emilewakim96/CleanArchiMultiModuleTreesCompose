package com.example.domain.repository

import com.example.domain.util.Resource
import com.example.common.entity.Tree

interface TreesRepository {

    suspend fun getTreesList(): Resource<List<Tree>>

    suspend fun saveTree(tree: Tree)

    fun refreshTrees()
}