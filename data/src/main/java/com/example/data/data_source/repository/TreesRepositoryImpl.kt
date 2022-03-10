package com.example.data.data_source.repository

import com.example.data.di.qualifier.RemoteData
import com.example.data.data_source.local.TreesLocalDataSource
import com.example.data.data_source.remote.TreesRemoteDataSource
import com.example.data.data_source.remote.responses.Tree
import com.example.data.di.qualifier.LocalData
import javax.inject.Inject

class TreesRepositoryImpl @Inject constructor(
    @LocalData private val localDataSource: TreesLocalDataSource,
    @RemoteData private val remoteDataSource: TreesRemoteDataSource,
): TreesRepository {

    override suspend fun getTreesList(forceRefresh: Boolean): List<Tree>? {
        return if (forceRefresh) {
            remoteDataSource.forceGetTreesList()
        } else {
            remoteDataSource.getTreesList()
        }.also { trees ->
            trees?.forEach { tree ->
                saveTreeInDB(tree)
            }
        }
    }

    override suspend fun getTreesListFromDB(): List<Tree> {
        return localDataSource.getTreesList()
    }

    override suspend fun saveTreeInDB(tree: Tree) {
        localDataSource.saveTree(tree)
    }

    override suspend fun deleteTreeFromDB(tree: Tree) {
        localDataSource.deleteTree(tree)
    }
}