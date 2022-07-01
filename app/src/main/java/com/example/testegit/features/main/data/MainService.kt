package com.example.testegit.features.main.data

import com.example.testegit.core.data.BaseResponse
import com.example.testegit.features.main.data.response.GitResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {

    @GET("search/repositories")
    suspend fun getGitList(
        @Query("q") search: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
    ): BaseResponse<GitResponse>

}