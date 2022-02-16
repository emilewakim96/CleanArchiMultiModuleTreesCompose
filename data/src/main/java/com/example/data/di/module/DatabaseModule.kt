package com.example.data.di.module

import android.app.Application
import androidx.room.Room
import com.example.data.data_source.local.TreeDao
import com.example.data.data_source.local.TreesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTreesDatabase(app: Application): TreesDatabase {
        return Room.databaseBuilder(
            app,
            TreesDatabase::class.java,
            TreesDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideTreesDao(database: TreesDatabase): TreeDao = database.treeDao
}