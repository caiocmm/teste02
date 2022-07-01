package com.example.testegit.core.data

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(

    @SerializedName("total_count")
    val total_count: Int = 0,

    @SerializedName("items")
    val items: List<T>
)