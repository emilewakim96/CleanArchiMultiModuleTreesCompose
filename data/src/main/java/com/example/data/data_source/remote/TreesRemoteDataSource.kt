package com.example.data.data_source.remote

import com.example.data.data_source.remote.mappers.mapRecordToTree
import com.example.domain.models.Tree
import com.example.domain.repository.TreesRepository
import com.example.domain.util.Resource
import javax.inject.Inject

class TreesRemoteDataSource @Inject constructor(
    private val api: TreesApi
): TreesRepository {

    override suspend fun getTreesList(): Resource<List<Tree>> {
        val response = try {
            api.getTreesList()
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        val trees = response.records?.map { it.mapRecordToTree() }
        return if (trees != null) {
            Resource.Success(trees)
        } else {
            Resource.Error("Response should not be null.")
        }
    }

    override suspend fun saveTree(tree: Tree) {}
}