package com.example.testegit.features.main.data.repository

import com.example.testegit.core.data.BaseResponse
import com.example.testegit.core.network.ResponseWrapper
import com.example.testegit.features.main.data.response.GitResponse

interface MainRepository {
    suspend fun getGitList(
        search: String,
        sort: String,
        page: Int
    ): ResponseWrapper<BaseResponse<GitResponse>>
}