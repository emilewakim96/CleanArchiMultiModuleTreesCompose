package com.example.domain.errorhandler

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection

class ErrorHandler {

    companion object {
        fun getError(throwable: Throwable): ErrorEntity {
            return when (throwable) {
                is IOException -> ErrorEntity.Network
                is HttpException -> {
                    when (throwable.code()) {
                        // no cache found in case of no network, thrown by retrofit -> treated as network error
                        HttpURLConnection.HTTP_CLIENT_TIMEOUT -> ErrorEntity.Network
                        HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound
                        HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable
                        HttpURLConnection.HTTP_BAD_GATEWAY -> ErrorEntity.Network
                        else -> ErrorEntity.Unknown
                    }
                }
                is ConnectException -> ErrorEntity.NotFound
                else -> ErrorEntity.Unknown
            }
        }
    }
}