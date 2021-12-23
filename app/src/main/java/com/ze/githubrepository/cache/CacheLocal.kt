package com.ze.githubrepository.cache

import com.ze.githubrepository.model.RepositoryModel

/*
Luquinhas comentou que isso seria um singleton, o cache só vai ser criado durante a vida do aplicativo,
depois que a aplicação morrer o cache morre junto, como se fosse uma memória RAM que só é
utilizada enquanto o computador está ligado
 */
object CacheLocal {

    val githubRepository = Cache<RepositoryModel.Items>()

}

class Cache<T> : HashMap<String, T>()