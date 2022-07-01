package com.example.testegit.features.main.data.response

import com.google.gson.annotations.SerializedName

data class GitResponse(

    @SerializedName("name")
    val name: String = "",

    @SerializedName("owner")
    val owner: GitOwner? = null,

    @SerializedName("stargazers_count")
    val stars: Int = 0,

    @SerializedName("forks_count")
    val forks: Int = 0
)

data class GitOwner(

    @SerializedName("login")
    val login: String = "",

    @SerializedName("avatar_url")
    val avatar: String = "",

)