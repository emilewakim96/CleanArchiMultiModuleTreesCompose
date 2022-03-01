package com.example.data.di.module

import android.app.Application
import android.content.Context
import com.example.data.data_source.manager.ConnectionManager
import com.example.data.data_source.remote.TreesApi
import com.example.data.data_source.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private fun buildOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
//            .addInterceptor(RequestInterceptor())
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = buildOkHttpClient()

    @Singleton
    @Provides
    fun provideTreesApi(okHttpClient: OkHttpClient): TreesApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(TreesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideConnectionManager(context: Context): ConnectionManager = ConnectionManager(context)
}