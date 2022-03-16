package com.example.domain.repository

import com.example.domain.entities.TreeEntity
import kotlinx.coroutines.flow.Flow

interface TreesRepository {

    suspend fun getTreesList(): Flow<List<TreeEntity>?>

    suspend fun saveTree(tree: TreeEntity)

    suspend fun deleteTree(tree: TreeEntity)
}