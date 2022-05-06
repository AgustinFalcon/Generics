package com.example.generics.retrofit.utils

import java.lang.Exception


sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T?) : ApiResponse<T>()
    data class Failure(val msg: String) : ApiResponse<Nothing>()
    data class Error(val e: Exception) : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()
}
