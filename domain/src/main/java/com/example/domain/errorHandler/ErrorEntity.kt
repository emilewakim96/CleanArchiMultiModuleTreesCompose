package com.example.domain.errorHandler

const val NETWORK_ERROR = "A network error has occurred"
const val NOT_FOUND_ERROR = "API endpoint not found"
const val SERVICE_UNAVAILABLE_ERROR = "Service Unavailable. Check your internet connection"
const val UNEXPECTED_ERROR = "An unexpected error has occurred. Please try again later"

sealed class ErrorEntity{
    object Network : ErrorEntity()
    object NotFound : ErrorEntity()
    object ServiceUnavailable : ErrorEntity()
    object Unknown : ErrorEntity()
}

fun ErrorEntity.showErrorMessage(): String {
    return when(this) {
        is ErrorEntity.Network -> NETWORK_ERROR
        is ErrorEntity.NotFound -> NOT_FOUND_ERROR
        is ErrorEntity.ServiceUnavailable -> SERVICE_UNAVAILABLE_ERROR
        else -> UNEXPECTED_ERROR
    }
}
