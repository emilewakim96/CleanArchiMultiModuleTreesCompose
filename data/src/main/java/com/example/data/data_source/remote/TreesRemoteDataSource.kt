package com.example.data.data_source.remote

import com.example.data.data_source.remote.mappers.mapRecordToTreeEntity
import com.example.domain.entities.TreeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TreesRemoteDataSource @Inject constructor(
    private val api: TreesApi
) {

    suspend fun getTreesList(): Flow<List<TreeEntity>?> = flow {
        emit(api.getTreesList().records?.map { it.mapRecordToTreeEntity() })
    }
}