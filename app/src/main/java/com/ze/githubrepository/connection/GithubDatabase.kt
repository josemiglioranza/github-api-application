package com.ze.githubrepository.connection

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ze.githubrepository.dao.GithubDao
import com.ze.githubrepository.model.RepositoryEntity

@Database(entities = [RepositoryEntity::class], version = 1, exportSchema = false)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun githubDao(): GithubDao

    companion object {

        var INSTANCE: GithubDatabase? = null

        fun getDatabase(context: Context): GithubDatabase {
            synchronized(true) {
                return Room.databaseBuilder(
                    context.applicationContext,
                    GithubDatabase::class.java,
                    "github_database"
                ).allowMainThreadQueries().build().also { INSTANCE = it }
            }
        }
    }
}