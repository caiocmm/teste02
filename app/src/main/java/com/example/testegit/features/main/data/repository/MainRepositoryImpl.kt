package com.example.testegit.features.main.data.repository

import com.example.testegit.core.data.BaseResponse
import com.example.testegit.core.db.DatabaseHelper
import com.example.testegit.core.db.dao.GitDataDAO
import com.example.testegit.core.network.ResponseWrapper
import com.example.testegit.core.network.apiCall
import com.example.testegit.features.main.data.MainService
import com.example.testegit.features.main.data.response.GitResponse

class MainRepositoryImpl(private val service: MainService, private val data: GitDataDAO) :
    MainRepository {

    override suspend fun getGitList(
        search: String,
        sort: String,
        page: Int
    ): ResponseWrapper<BaseResponse<GitResponse>> {

        val databaseItems = data.getFields(page)

        return if (databaseItems.isNotEmpty()){

            val dataMap = DatabaseHelper.convertToGitResponse(databaseItems)
            ResponseWrapper.Success(BaseResponse(dataMap.size, dataMap))

        } else {

            val apiData = service.getGitList(search, sort, page)
            data.insertResponse(DatabaseHelper.convertToGitData(apiData.items, page))

            apiCall {
                apiData
            }
        }
    }
}