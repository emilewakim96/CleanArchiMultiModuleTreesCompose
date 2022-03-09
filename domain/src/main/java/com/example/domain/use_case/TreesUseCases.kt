package com.example.domain.use_case

data class TreesUseCases(
    val getTreesUseCase: GetTreesUseCase,
    val deleteTreeUseCase: DeleteTreeUseCase,
    val addTreeUseCase: AddTreeUseCase
)