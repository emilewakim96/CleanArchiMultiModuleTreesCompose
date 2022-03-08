package com.example.domain.util

import com.example.domain.errorHandler.ErrorEntity

sealed class Resource<T>(val data: T? = null, val error: ErrorEntity? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(error: ErrorEntity?, data: T? = null) : Resource<T>(data, error)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}