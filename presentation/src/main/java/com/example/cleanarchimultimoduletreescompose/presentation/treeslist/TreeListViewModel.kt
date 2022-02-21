package com.example.cleanarchimultimoduletreescompose.presentation.treeslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchimultimoduletreescompose.presentation.util.UIState
import com.example.domain.use_case.TreesUseCases
import com.example.data.data_source.util.DispatcherProvider
import com.example.domain.models.Tree
import com.example.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreeListViewModel @Inject constructor(
    private val treesUseCases: TreesUseCases,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    private val _treesList= MutableStateFlow<MutableList<Tree>>(mutableListOf())
    val treesList = _treesList.asStateFlow() // returns immutable value of state flow, observe this value in the view

    private val _uiState = MutableStateFlow(UIState.LOADING)
    val uiState = _uiState.asStateFlow()

    init {
        loadTreeList()
    }

    fun loadTreeList() {
        viewModelScope.launch(dispatcher.main) {
            _uiState.value = UIState.LOADING
            when(val result = treesUseCases.getTreesUseCase()) {
                is Resource.Success -> {
                    val trees = result.data
                    trees?.let {
                        _treesList.value = it.toMutableList()
                    }
                    _uiState.value = UIState.SUCCESS
                }
                else -> {
                    _uiState.value = UIState.NETWORK_ERROR
                }
            }
        }
    }
}