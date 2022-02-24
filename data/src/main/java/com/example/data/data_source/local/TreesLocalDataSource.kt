package com.example.data.data_source.local

import com.example.data.data_source.TreesDataSource
import com.example.data.data_source.local.helper.RealmDBOperations
import com.example.data.data_source.remote.mappers.mapToTreeRealm
import com.example.domain.models.Tree
import com.example.domain.util.Resource
import javax.inject.Inject

class TreesLocalDataSource @Inject constructor(
    private val realmDBOperations: RealmDBOperations
): TreesDataSource {

    override suspend fun getTreesList(): Resource<List<Tree>> {
        val response = try {
            realmDBOperations.getTrees()
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }

    override suspend fun insertTree(tree: Tree) {
        realmDBOperations.insertTree(tree.mapToTreeRealm())
    }
}