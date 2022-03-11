package com.example.data.data_source.repository

import com.example.data.data_source.remote.responses.Tree

class FakeTreesRepository: TreesRepository {

    private val trees = mutableListOf<Tree>()

    override suspend fun getTreesList(forceRefresh: Boolean): List<Tree>? {
        return trees
    }

    override suspend fun getTreesListFromDB(): List<Tree>? {
        return trees
    }

    override suspend fun saveTreeInDB(tree: Tree) {
        trees.add(tree)
    }

    override suspend fun deleteTreeFromDB(tree: Tree) {
        trees.remove(tree)
    }
}