package com.example.data.data_source.repository

import com.example.data.data_source.remote.responses.Tree

interface TreesRepository {

    suspend fun getTreesList(): List<Tree>?

    suspend fun saveTreeInDB(tree: Tree)

    suspend fun deleteTreeFromDB(tree: Tree)
}