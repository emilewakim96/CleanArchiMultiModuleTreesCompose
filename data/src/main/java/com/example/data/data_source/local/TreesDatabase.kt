package com.example.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.data_source.remote.responses.Tree
import com.example.data.data_source.remote.typeconverter.FieldsTypeConverter
import com.example.data.data_source.remote.typeconverter.GeometryTypeConverter

@Database(
    entities = [Tree::class],
    version = 1
)
@TypeConverters(
    FieldsTypeConverter::class, GeometryTypeConverter::class,
)
abstract class TreesDatabase: RoomDatabase() {
    abstract val treeDao: TreeDao

    companion object {
        const val DATABASE_NAME = "trees_db"
    }
}