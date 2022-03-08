package com.example.data.data_source.remote

import com.example.data.data_source.remote.mappers.mapRecordToTree
import com.example.data.data_source.remote.responses.Tree
import javax.inject.Inject

class TreesRemoteDataSource @Inject constructor(
    private val api: TreesApi
) {

    suspend fun getTreesList(): List<Tree> {
        val response = try {
            api.getTreesList().records?.map { it.mapRecordToTree() }
        } catch(e: Exception) {
            throw Throwable(e.message)
        }
        return response ?: throw Throwable("Invalid response")
    }
}