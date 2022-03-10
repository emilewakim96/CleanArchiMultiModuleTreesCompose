package com.example.data.data_source.remote

import com.example.data.data_source.remote.mappers.mapRecordToTree
import com.example.data.data_source.remote.responses.Tree
import javax.inject.Inject

class TreesRemoteDataSource @Inject constructor(
    private val api: TreesApi
) {

    suspend fun getTreesList(): List<Tree>? {
        return api.getTreesList().records?.map { it.mapRecordToTree() }
    }

    suspend fun forceGetTreesList(): List<Tree>? {
        return api.forceGetTreesList().records?.map { it.mapRecordToTree() }
    }
}