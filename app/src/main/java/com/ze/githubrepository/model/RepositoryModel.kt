package com.ze.githubrepository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class RepositoryModel{

    @Parcelize
    data class Github(
        @SerializedName("name") val name: String,
        @SerializedName("forks_count") val numberOfForks: Long,
        @SerializedName("stargazers_count") val numberOfWatchers: Long,
        @SerializedName("owner") val owner: Person,
        @SerializedName("description") val descricao: String
    ) : Parcelable

    data class Items(
        @SerializedName("items") val items: List<Github>
    )

    @Parcelize
    data class Person(
        @SerializedName("avatar_url") val photoPath: String,
        @SerializedName("login") val name: String
    ) : Parcelable
}
