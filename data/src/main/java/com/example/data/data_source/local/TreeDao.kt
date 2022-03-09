package com.example.data.data_source.local

import androidx.room.*
import com.example.data.data_source.remote.responses.Tree
import kotlinx.coroutines.flow.Flow

@Dao
interface TreeDao {

    @Query("SELECT * FROM tree")
    fun getTrees(): Flow<List<Tree>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTree(tree: Tree)

    @Query("DELETE FROM tree WHERE id= :tree_id")
    suspend fun deleteTree(tree_id: String)
}