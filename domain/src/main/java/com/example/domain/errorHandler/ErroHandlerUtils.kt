package com.example.domain.errorHandler

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection

const val NETWORK_ERROR = "A network error has occurred"
const val NOT_FOUND_ERROR = "API endpoint not found"
const val SERVICE_UNAVAILABLE_ERROR = "Service Unavailable. Check your internet connection"
const val UNEXPECTED_ERROR = "An unexpected error has occurred. Please try again later"

fun ErrorEntity.showErrorMessage(): String {
    return when(this) {
        is ErrorEntity.Network -> NETWORK_ERROR
        is ErrorEntity.NotFound -> NOT_FOUND_ERROR
        is ErrorEntity.ServiceUnavailable -> SERVICE_UNAVAILABLE_ERROR
        else -> UNEXPECTED_ERROR
    }
}

fun Throwable.getError(): ErrorEntity {
    return when (this) {
        is IOException -> ErrorEntity.Network
        is HttpException -> {
            when (this.code()) {
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