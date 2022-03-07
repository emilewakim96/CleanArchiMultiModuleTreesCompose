package com.example.domain.repository

import com.example.domain.util.Resource
import com.example.domain.entities.TreeEntity

interface TreesRepository {

    suspend fun getTreesList(): Resource<List<TreeEntity>>

    suspend fun saveTree(tree: TreeEntity)
}