package com.example.cleanarchimultimoduletreescompose.presentation.treeslist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.cleanarchimultimoduletreescompose.presentation.base.BaseViewModel
import com.example.data.data_source.manager.ConnectionManager
import com.example.domain.use_case.TreesUseCases
import com.example.data.data_source.util.DispatcherProvider
import com.example.domain.entities.TreeEntity
import com.example.domain.errorhandler.showErrorMessage
import com.example.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreeListViewModel @Inject constructor(
    private val treesUseCases: TreesUseCases,
    private val dispatcher: DispatcherProvider,
    connectionManager: ConnectionManager
) : BaseViewModel(connectionManager) {

    var treesList = mutableStateListOf<TreeEntity>()
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        loadTreeList()
    }

    fun loadTreeList() {
        viewModelScope.launch(dispatcher.main) {
            isLoading.value = true
            when(val result = treesUseCases.getTreesUseCase()) {
                is Resource.Success -> {
                    val trees = result.data
                    trees?.let {
                        treesList.addAll(it)
                    }
                    loadError.value = ""
                    isLoading.value = false
                }
                else -> {
                    loadError.value = result.error?.showErrorMessage() ?: "Invalid response"
                    isLoading.value = false
                }
            }
        }
    }
}