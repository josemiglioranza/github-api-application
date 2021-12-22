package com.ze.githubrepository.connection

import com.ze.githubrepository.model.RepositoryModel
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {

    @GET("/search/repositories?q=language:java&sort=stars")
    fun getRepositories(): Call<RepositoryModel.Items>
}