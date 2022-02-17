package com.example.data.data_source

import com.example.domain.models.Tree
import com.example.domain.util.Resource

interface TreesDataSource {

    suspend fun getTreesList(): Resource<List<Tree>>

    suspend fun insertTree(tree: Tree)
}