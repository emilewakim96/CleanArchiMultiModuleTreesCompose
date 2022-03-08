package com.example.data.data_source.local

import com.example.data.data_source.remote.responses.Tree

import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TreesLocalDataSource @Inject constructor(
    private val treesDao: TreeDao
) {

    suspend fun getTreesList(): List<Tree> {
       return treesDao.getTrees().first()
    }

    suspend fun saveTree(tree: Tree) {
        treesDao.saveTree(tree)
    }
}