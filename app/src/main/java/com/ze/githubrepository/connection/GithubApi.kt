package com.ze.githubrepository.connection

import com.ze.githubrepository.model.Github
import com.ze.githubrepository.model.Items
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {

    @GET("/search/repositories?q=language:java&sort=stars")
    fun getRepositories(): Call<Items>
}