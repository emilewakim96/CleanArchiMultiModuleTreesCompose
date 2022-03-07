package com.example.data.di.module

import com.example.data.data_source.local.TreeDao
import com.example.data.data_source.local.TreesLocalDataSource
import com.example.data.data_source.manager.ConnectionManager
import com.example.data.data_source.remote.TreesApi
import com.example.data.data_source.remote.TreesRemoteDataSource
import com.example.data.data_source.repository.TreesRepositoryImpl
import com.example.data.di.qualifier.LocalData
import com.example.data.di.qualifier.RemoteData
import com.example.domain.repository.TreesRepository
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
        remoteDataSource: TreesRemoteDataSource,
        connectionManager: ConnectionManager
    ): TreesRepository = TreesRepositoryImpl(localDataSource, remoteDataSource, connectionManager)


    @Singleton
    @Provides
    @LocalData
    fun provideLocalDataSource(treesDao: TreeDao): TreesRepository = TreesLocalDataSource(treesDao)

    @Singleton
    @Provides
    @RemoteData
    fun provideRemoteDataSource(treesApi: TreesApi): TreesRepository = TreesRemoteDataSource(treesApi)
}