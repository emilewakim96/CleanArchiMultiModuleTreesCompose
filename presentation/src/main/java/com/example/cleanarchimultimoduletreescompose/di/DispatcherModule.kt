package com.example.cleanarchimultimoduletreescompose.di

import com.example.domain.util.DefaultDispatchers
import com.example.domain.util.DispatcherProvider
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