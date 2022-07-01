package com.example.testegit.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testegit.core.db.dao.GitDataDAO
import com.example.testegit.core.db.entity.GitData

@Database(
    entities = [GitData::class],
    version = 1
)

abstract class GitDatabase : RoomDatabase() {
    abstract fun gitDataDAO(): GitDataDAO
}