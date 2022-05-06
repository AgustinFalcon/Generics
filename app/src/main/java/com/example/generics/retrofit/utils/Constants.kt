package com.example.generics.retrofit.utils

import retrofit2.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception


fun <T> result(call: suspend () -> Response<T>) : Flow<ApiResponse<T?>> = flow {

    emit(ApiResponse.Loading)

    try {
        //here is the request call
        val c  = call()
        c.let { a ->
            if (c.isSuccessful) {
                emit(ApiResponse.Success(a.body()))
            } else {
                c.errorBody()?.let { error ->
                    error.close()
                    emit(ApiResponse.Failure(error.toString()))
                }
            }
        }

    } catch (t:Throwable) {
        t.printStackTrace()
        emit(ApiResponse.Failure(t.message.toString()))

    } catch (e: Exception) {
        emit(ApiResponse.Error(e))
    }

}