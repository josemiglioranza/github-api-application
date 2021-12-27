package com.ze.githubrepository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ze.githubrepository.model.RepositoryEntity

@Dao
interface GithubDao {

    @Insert
    fun save(repository: List<RepositoryEntity>)

    @Query("SELECT * FROM github_repository")
    fun findGithub(): List<RepositoryEntity>
}