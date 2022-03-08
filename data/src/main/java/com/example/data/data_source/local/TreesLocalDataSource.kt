package com.example.data.data_source.local

import com.example.data.data_source.remote.mappers.mapToEntity
import com.example.data.data_source.remote.mappers.mapToModel
import com.example.domain.entities.TreeEntity
import com.example.domain.repository.TreesRepository
import com.example.domain.util.Resource

import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TreesLocalDataSource @Inject constructor(
    private val treesDao: TreeDao
) {

    suspend fun getTreesList(): List<TreeEntity> {
        return treesDao.getTrees().map { it.mapToEntity() }
    }

    suspend fun saveTree(tree: TreeEntity) {
        treesDao.saveTree(tree.mapToModel())
    }
}