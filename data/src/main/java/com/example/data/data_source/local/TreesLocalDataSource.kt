package com.example.data.data_source.local

import com.example.data.data_source.remote.mappers.mapToEntity
import com.example.data.data_source.remote.mappers.mapToModel
import com.example.domain.entities.TreeEntity

import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TreesLocalDataSource @Inject constructor(
    private val treesDao: TreeDao
) {

    suspend fun getTreesList(): List<TreeEntity> {
        val response = try {
            treesDao.getTrees().first().map { it.mapToEntity() }
        } catch(e: Exception) {
            throw Throwable(e.message)
        }
        return response
    }

    suspend fun saveTree(tree: TreeEntity) {
        treesDao.saveTree(tree.mapToModel())
    }
}