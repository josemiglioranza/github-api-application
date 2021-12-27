package com.ze.githubrepository.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_repository")
class RepositoryEntity(
    @PrimaryKey val name: String,
    val numberOfForks: Long,
    val numberOfWatchers: Long,
    val descricao: String,
    val photoPath: String,
    val ownerName: String
) {
    constructor(repository: RepositoryModel.Github): this(
        name = repository.name,
        descricao = repository.descricao,
        numberOfForks = repository.numberOfForks,
        numberOfWatchers = repository.numberOfWatchers,
        photoPath = repository.owner.photoPath,
        ownerName = repository.owner.name
    )
}
