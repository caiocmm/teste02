package com.example.testegit.core.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun <T> apiCall(apiCall: suspend () -> T): ResponseWrapper<T> {

    val errorRequest = "Erro ao efetuar requisição"

    return withContext(Dispatchers.IO) {
        try {
            ResponseWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    val code = throwable.code()
                    ResponseWrapper.Error(code, errorRequest)
                }
                else -> {
                    ResponseWrapper.Error(null, errorRequest)
                }
            }
        }
    }
}

