package com.example.data.data_source.local

import androidx.room.*
import com.example.domain.models.Tree
import kotlinx.coroutines.flow.Flow

@Dao
interface TreeDao {

    @Query("SELECT * FROM tree")
    fun getTrees(): Flow<List<Tree>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTree(tree: Tree)

    @Query("DELETE FROM tree")
    suspend fun clearTrees()
}