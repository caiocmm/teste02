package com.example.testegit.core.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testegit.core.db.DatabaseConstants
import com.example.testegit.core.db.entity.GitData

@Dao
interface GitDataDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResponse(data: List<GitData>)

    @Query("SELECT * FROM ${DatabaseConstants.DATA_ENTITY} WHERE page = :page")
    suspend fun getFields(page: Int) : List<GitData>

    @Query("DELETE FROM ${DatabaseConstants.DATA_ENTITY}")
    suspend fun deleteAll()
}