package com.example.domain.manager

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
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
        isOffline()
        registerNetworkCallback()
    }

    fun addListener(listener: ConnectionManagerListener) {
        listeners.add(listener)
        listener.onConnectionChanged(if (offline) ConnectionManagerListener.ConnectionState.OFFLINE else ConnectionManagerListener.ConnectionState.ONLINE)
    }

    fun removeListener(listener: ConnectionManagerListener) {
        listeners.remove(listener)
    }

    private fun registerNetworkCallback() {
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
        }
    }

    @Suppress("DEPRECATION")
    private fun isOffline() {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    offline = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> false
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> false
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> false
                        else -> true
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        offline = false
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        offline = false
                    }
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