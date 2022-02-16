package com.example.data.data_source.remote

import com.example.data.data_source.TreesDataSource
import com.example.common.entity.Tree
import com.example.data.data_source.remote.responses.mapRecordToTree
import com.example.domain.util.Resource
import javax.inject.Inject

class TreesRemoteDataSource @Inject constructor(
    private val api: TreesApi
): TreesDataSource {

    override suspend fun getTreesList(): Resource<List<Tree>> {
        val response = try {
            api.getTreesList()
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        val trees = response.records?.map { mapRecordToTree(it) }
        return if (trees != null) {
            Resource.Success(trees)
        } else {
            Resource.Error("Response should not be null.")
        }
    }

    override suspend fun insertTree(tree: Tree) {}
}