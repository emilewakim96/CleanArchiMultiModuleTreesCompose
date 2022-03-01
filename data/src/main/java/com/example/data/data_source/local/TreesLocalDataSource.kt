package com.example.data.data_source.local

import com.example.domain.models.Tree
import com.example.domain.repository.CacheEntry
import com.example.domain.repository.LocalDataSource
import com.example.domain.util.Resource

import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TreesLocalDataSource @Inject constructor(
    private val treesDao: TreeDao
): LocalDataSource<CacheEntry<Resource<List<Tree>>>> {

    private suspend fun getTreesList(): Resource<List<Tree>> {
        val response = try {
            treesDao.getTrees()
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response.first())
    }

    private suspend fun insertTree(tree: Tree) {
        treesDao.insertTree(tree)
    }

    override suspend fun get(): CacheEntry<Resource<List<Tree>>> {
        val cachedTrees = getTreesList()
        return CacheEntry(cachedTrees)
    }

    override suspend fun set(cacheEntry: CacheEntry<Resource<List<Tree>>>) {
        val trees =  cacheEntry.value.data
        trees?.forEach {
            insertTree(it)
        }
    }

    override suspend fun clear() {
        treesDao.clearTrees()
    }

}