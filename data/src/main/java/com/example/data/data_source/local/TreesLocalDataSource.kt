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
): TreesRepository {

    override suspend fun getTreesList(): Resource<List<TreeEntity>> {
        val response = try {
            treesDao.getTrees()
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response.first().map { it.mapToEntity() })
    }

    override suspend fun saveTree(tree: TreeEntity) {
        treesDao.saveTree(tree.mapToModel())
    }
}