package com.example.testegit.core.db

import com.example.testegit.core.db.entity.GitData
import com.example.testegit.features.main.data.response.GitOwner
import com.example.testegit.features.main.data.response.GitResponse

object DatabaseHelper {

    fun convertToGitData(data: List<GitResponse>, page: Int): List<GitData> {
        return data.map {
            GitData(
                name = it.name,
                owner = GitOwner(
                    login = it.owner?.login.toString(),
                    avatar = it.owner?.avatar.toString()
                ),
                stars = it.stars,
                forks = it.forks,
                page = page
            )
        }
    }

    fun convertToGitResponse(data: List<GitData>): List<GitResponse> {
        return data.map {
            GitResponse(
                name = it.name,
                owner = GitOwner(
                    login = it.owner?.login.toString(),
                    avatar = it.owner?.avatar.toString()
                ),
                stars = it.stars,
                forks = it.forks
            )
        }
    }

}