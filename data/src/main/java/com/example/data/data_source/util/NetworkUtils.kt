package com.example.data.data_source.util

import android.content.Context
import android.net.ConnectivityManager

class NetworkUtils {
    companion object {
        fun thereIsConnection(context: Context): Boolean {
            // Get a reference to the ConnectivityManager to check state of network connectivity
            val connMgr =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            // Get details on the currently active default data network
            val networkInfo = connMgr.activeNetworkInfo
            // If there is a network connection, return true, otherwise false
            return networkInfo != null && networkInfo.isConnected
        }
    }
}