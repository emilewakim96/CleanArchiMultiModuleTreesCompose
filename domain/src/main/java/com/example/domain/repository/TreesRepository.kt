package com.example.domain.repository

import com.example.domain.entities.TreeEntity

interface TreesRepository {

    suspend fun getTreesList(): List<TreeEntity>?

    suspend fun saveTree(tree: TreeEntity)

    suspend fun deleteTree(tree: TreeEntity)
}