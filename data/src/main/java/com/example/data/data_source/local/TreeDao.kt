package com.example.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.data_source.remote.responses.Tree
import kotlinx.coroutines.flow.Flow

@Dao
interface TreeDao {

    @Query("SELECT * FROM tree")
    fun getTrees(): List<Tree>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTree(tree: Tree)

}