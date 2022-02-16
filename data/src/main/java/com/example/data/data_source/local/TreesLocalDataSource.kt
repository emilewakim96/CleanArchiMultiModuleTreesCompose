package com.example.data.data_source.local

import com.example.data.data_source.TreesDataSource
import com.example.common.entity.Tree
import com.example.domain.util.Resource

import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TreesLocalDataSource @Inject constructor(
    private val treesDao: TreeDao
): TreesDataSource {

    override suspend fun getTreesList(): Resource<List<Tree>> {
        val response = try {
            treesDao.getTrees()
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response.first())
    }

    override suspend fun insertTree(tree: Tree) {
        treesDao.insertTree(tree)
    }
}