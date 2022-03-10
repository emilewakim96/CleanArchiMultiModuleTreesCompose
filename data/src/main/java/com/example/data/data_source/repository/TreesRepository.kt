package com.example.data.data_source.repository

import com.example.data.data_source.remote.responses.Tree

interface TreesRepository {

    /* api */
    suspend fun getTreesList(forceRefresh: Boolean): List<Tree>?

    /* room */
    suspend fun getTreesListFromDB(): List<Tree>?

    suspend fun saveTreeInDB(tree: Tree)

    suspend fun deleteTreeFromDB(tree: Tree)
}