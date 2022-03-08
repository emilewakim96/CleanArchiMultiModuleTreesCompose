package com.example.domain.di

import com.example.data.data_source.util.DefaultDispatchers
import com.example.data.data_source.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Singleton
    @Provides
    fun provideDispatcher(): DispatcherProvider = DefaultDispatchers()
}