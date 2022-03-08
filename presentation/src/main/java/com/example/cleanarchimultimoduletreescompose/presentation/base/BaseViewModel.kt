package com.example.cleanarchimultimoduletreescompose.presentation.base

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.manager.ConnectionManager

open class BaseViewModel(
    private val connectionManager: ConnectionManager
): ViewModel(), ConnectionManager.ConnectionManagerListener {

    var isOffline = mutableStateOf(connectionManager.offline)

    init {
        connectionManager.addListener(this)
    }

    override fun onConnectionChanged(state: ConnectionManager.ConnectionManagerListener.ConnectionState) {
        isOffline.value = state == ConnectionManager.ConnectionManagerListener.ConnectionState.OFFLINE
    }

    override fun onCleared() {
        super.onCleared()
        connectionManager.removeListener(this)
    }
}