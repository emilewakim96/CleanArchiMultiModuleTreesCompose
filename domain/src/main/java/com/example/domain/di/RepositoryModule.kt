package com.example.domain.di

import android.content.Context
import com.example.domain.managers.ConnectionManager
import com.example.data.data_source.local.TreeDao
import com.example.data.data_source.local.TreesLocalDataSource
import com.example.data.data_source.remote.TreesApi
import com.example.data.data_source.remote.TreesRemoteDataSource
import com.example.data.data_source.repository.TreesRepositoryImpl
import com.example.data.di.qualifier.LocalData
import com.example.data.di.qualifier.RemoteData
import com.example.data.data_source.repository.TreesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideTreesRepository(
        localDataSource: TreesLocalDataSource,
        remoteDataSource: TreesRemoteDataSource
    ): TreesRepository = TreesRepositoryImpl(localDataSource, remoteDataSource)

    @Singleton
    @Provides
    fun provideConnectionManager(context: Context): ConnectionManager =
        ConnectionManager(context)

    @Singleton
    @Provides
    @LocalData
    fun provideLocalDataSource(treesDao: TreeDao): TreesLocalDataSource = TreesLocalDataSource(treesDao)

    @Singleton
    @Provides
    @RemoteData
    fun provideRemoteDataSource(treesApi: TreesApi): TreesRemoteDataSource = TreesRemoteDataSource(treesApi)
}