package com.example.data.data_source.remote

import com.example.data.data_source.remote.mappers.mapRecordToTreeEntity
import com.example.domain.entities.TreeEntity
import javax.inject.Inject

class TreesRemoteDataSource @Inject constructor(
    private val api: TreesApi
) {

    suspend fun getTreesList(): List<TreeEntity> {
        val response = try {
            api.getTreesList().records?.map { it.mapRecordToTreeEntity() }
        } catch(e: Exception) {
            throw Throwable(e.message)
        }
        return response ?: throw Throwable("Invalid response")
    }
}