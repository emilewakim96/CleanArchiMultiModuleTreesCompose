package com.example.data.data_source.manager

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build

class ConnectionManager(private val context: Context) {

    interface ConnectionManagerListener {

        enum class ConnectionState {
            ONLINE,
            OFFLINE
        }

        fun onConnectionChanged(state: ConnectionState)
    }


    //State. Updated when connection updated.
    var offline = true
    private val listeners = mutableListOf<ConnectionManagerListener>()

    init {
        getConnection()
    }

    fun addListener(listener: ConnectionManagerListener) {
        listeners.add(listener)
        listener.onConnectionChanged(if (offline) ConnectionManagerListener.ConnectionState.OFFLINE else ConnectionManagerListener.ConnectionState.ONLINE)
    }

    fun removeListener(listener: ConnectionManagerListener) {
        listeners.remove(listener)
    }

    @Suppress("DEPRECATION")
    private fun getConnection() {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(object :
                ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    try {
                        offline = false
                        notifyListeners(ConnectionManagerListener.ConnectionState.ONLINE)
                    } catch (e: Exception) {
                        println("Too soon")
                    }

                }

                override fun onLost(network: Network) {
                    offline = true
                    notifyListeners(ConnectionManagerListener.ConnectionState.OFFLINE)
                }
            })
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    offline = !(type == ConnectivityManager.TYPE_WIFI || type == ConnectivityManager.TYPE_MOBILE)
                }
            }
        }

    }

    private fun notifyListeners(state: ConnectionManagerListener.ConnectionState) {
        for (listener in listeners) {
            listener.onConnectionChanged(state)
        }
    }
}

