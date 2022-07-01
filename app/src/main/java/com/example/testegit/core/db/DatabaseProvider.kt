package com.example.testegit.core.db

import android.app.Application
import androidx.room.Room
import com.example.testegit.core.db.dao.GitDataDAO

object DatabaseProvider {

    fun provideAppDb(app: Application): GitDatabase {
        return Room.databaseBuilder(
            app,
            GitDatabase::class.java,
            DatabaseConstants.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    fun provideFieldsDao(db: GitDatabase): GitDataDAO {
        return db.gitDataDAO()
    }
}