package com.example.domain.di

import com.example.domain.repository.TreesRepository
import com.example.domain.use_case.DeleteTreesUseCase
import com.example.domain.use_case.GetTreesUseCase
import com.example.domain.use_case.TreesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideTreesUseCases(repository: TreesRepository): TreesUseCases {
        return TreesUseCases(
            getTreesUseCase = GetTreesUseCase(repository),
            deleteTreesUseCase = DeleteTreesUseCase(repository)
        )
    }
}