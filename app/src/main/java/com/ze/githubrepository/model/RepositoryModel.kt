package com.ze.githubrepository.model

import com.google.gson.annotations.SerializedName

data class Github(
    @SerializedName("name") val name: String,
    @SerializedName("forks_count") val numberOfForks: Long,
    @SerializedName("stargazers_count") val numberOfWatchers: Long,
    @SerializedName("owner") val owner: Person,
    @SerializedName("description") val descricao: String
)

data class Items(
    @SerializedName("items") val items: List<Github>
)

data class Person(
    @SerializedName("avatar_url") val photoPath: String,
    @SerializedName("login") val name: String
)