package com.example.data.data_source.local

import com.example.data.data_source.remote.responses.Tree

import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TreesLocalDataSource @Inject constructor(
    private val treesDao: TreeDao
) {

    suspend fun getTreesList(): List<Tree> {
        val response = try {
            treesDao.getTrees().first()
        } catch(e: Exception) {
            throw Throwable(e.message)
        }
        return response
    }

    suspend fun saveTree(tree: Tree) {
        treesDao.saveTree(tree)
    }
}