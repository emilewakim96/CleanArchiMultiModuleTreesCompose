package com.example.cleanarchimultimoduletreescompose.presentation.base

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.managers.ConnectionManager
import com.example.domain.fetchstrategy.FetchStrategy

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

    protected fun getFetchStrategy(force : Boolean) : FetchStrategy {
        return when (!connectionManager.offline) {
            true -> {
                if(force) FetchStrategy.Remote
                else FetchStrategy.Cache
            }
            false -> FetchStrategy.Local
        }
    }
}