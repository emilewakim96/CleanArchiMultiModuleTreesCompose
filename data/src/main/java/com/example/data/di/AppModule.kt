package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.di.qualifier.LocalData
import com.example.data.di.qualifier.RemoteData
import com.example.data.data_source.local.TreesDatabase
import com.example.data.data_source.remote.TreesApi
import com.example.domain.use_case.GetTreesUseCase
import com.example.data.data_source.TreesDataSource
import com.example.data.data_source.local.TreeDao
import com.example.data.data_source.local.TreesLocalDataSource
import com.example.data.data_source.remote.TreesRemoteDataSource
import com.example.data.data_source.repository.TreesRepositoryImpl
import com.example.data.data_source.util.Constants.BASE_URL
import com.example.domain.repository.TreesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {



//    @Singleton
//    @Provides
//    fun provideTreesRepository(
//        localDataSource: TreesLocalDataSource,
//        remoteDataSource: TreesRemoteDataSource
//    ): TreesRepository = TreesRepositoryImpl(localDataSource, remoteDataSource)






//}