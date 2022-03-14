package com.example.cleanarchimultimoduletreescompose.data.di

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
object TestDatabaseModule {

    @Provides
    @Singleton
    fun provideTreesDatabase(app: Application): TreesDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            TreesDatabase::class.java,
        ).build()
    }

    @Singleton
    @Provides
    fun provideTreesDao(database: TreesDatabase): TreeDao = database.treeDao
}