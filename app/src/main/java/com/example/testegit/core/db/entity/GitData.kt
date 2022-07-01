package com.example.testegit.core.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testegit.core.db.DatabaseConstants
import com.example.testegit.features.main.data.response.GitOwner

@Entity(
    tableName = DatabaseConstants.DATA_ENTITY
)
data class GitData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    @Embedded
    val owner: GitOwner?,
    val stars: Int = 0,
    val forks: Int = 0,
    val page: Int = 0
)