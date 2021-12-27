package com.ze.githubrepository.model

import android.os.Parcelable
import androidx.room.Entity
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
    ) : Parcelable {
        constructor(repositoryEntity: RepositoryEntity): this(
            name = repositoryEntity.name,
            descricao = repositoryEntity.descricao,
            numberOfForks = repositoryEntity.numberOfForks,
            numberOfWatchers = repositoryEntity.numberOfWatchers,
            owner = Person(
                photoPath = repositoryEntity.photoPath,
                name = repositoryEntity.ownerName
            )
        )
    }

    data class Items(
        @SerializedName("items") val items: List<Github>
    )

    @Parcelize
    data class Person(
        @SerializedName("avatar_url") val photoPath: String,
        @SerializedName("login") val name: String
    ) : Parcelable
}
