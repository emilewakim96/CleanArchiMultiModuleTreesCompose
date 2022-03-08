package com.example.data.data_source.repository

import com.example.data.data_source.remote.responses.Tree

interface TreesRepository {

    suspend fun getTreesList(): List<Tree>

    suspend fun saveTree(tree: Tree)
}