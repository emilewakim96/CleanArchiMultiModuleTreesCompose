package com.example.data.di.module

import android.app.Application
import android.content.Context
import com.example.data.data_source.local.TreeDao
import com.example.data.data_source.local.TreesLocalDataSource
import com.example.data.data_source.remote.TreesApi
import com.example.data.data_source.remote.TreesRemoteDataSource
import com.example.data.data_source.repository.TreesRepositoryImpl
import com.example.data.di.qualifier.LocalData
import com.example.data.di.qualifier.RemoteData
import com.example.domain.models.Tree
import com.example.domain.repository.CacheEntry
import com.example.domain.repository.LocalDataSource
import com.example.domain.repository.RemoteDataSource
import com.example.domain.repository.TreesRepository
import com.example.domain.util.Resource
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
        context: Context
    ): TreesRepository = TreesRepositoryImpl(localDataSource, remoteDataSource, context)

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    @LocalData
    fun provideLocalDataSource(treesDao: TreeDao): LocalDataSource<CacheEntry<Resource<List<Tree>>>> = TreesLocalDataSource(treesDao)

    @Singleton
    @Provides
    @RemoteData
    fun provideRemoteDataSource(treesApi: TreesApi): RemoteDataSource<Resource<List<Tree>>> = TreesRemoteDataSource(treesApi)
}