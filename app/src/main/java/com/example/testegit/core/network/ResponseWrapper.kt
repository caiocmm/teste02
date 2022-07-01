package com.example.testegit.core.network

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val value: T): ResponseWrapper<T>()
    data class Error(val code: Int? = null, val error: String = ""): ResponseWrapper<Nothing>()
}