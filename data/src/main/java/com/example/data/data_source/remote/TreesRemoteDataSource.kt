package com.example.data.data_source.remote

import com.example.data.data_source.remote.mappers.mapRecordToTreeEntity
import com.example.domain.entities.TreeEntity
import com.example.domain.repository.TreesRepository
import com.example.domain.util.Resource
import javax.inject.Inject

class TreesRemoteDataSource @Inject constructor(
    private val api: TreesApi
) {

    suspend fun getTreesList(): Resource<List<TreeEntity>> {
        val response = try {
            api.getTreesList()
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        val trees = response.records?.map { it.mapRecordToTreeEntity() }
        return if (trees != null) {
            Resource.Success(trees)
        } else {
            Resource.Error("Response should not be null.")
        }
    }
}