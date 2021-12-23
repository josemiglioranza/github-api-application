package com.ze.githubrepository.extensions

import com.ze.githubrepository.cache.Cache
import com.ze.githubrepository.cache.CacheDisk.exist
import com.ze.githubrepository.cache.CacheDisk.find
import com.ze.githubrepository.cache.CacheDisk.save
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.makeAsyncOperation(
    tag: String,
    cache: Cache<T>,
    onSuccess: (T) -> Unit,
    onError: (Throwable) -> Unit = {}
) {

    if (cache.containsKey(tag)) {
        cache[tag]?.let(onSuccess)
        return
    }
}

fun <T>Call<T>.doRequest(
    tag:String,
    clazz: Class<T>,
    onSuccess: (T) -> Unit,
    onError: (Throwable) -> Unit = {}
) {
    if(exist(tag)) {
        onSuccess(find(tag, clazz))
        return
    }

    enqueueWithCache(
        onSuccess = onSuccess,
        onError = onError,
        storeData = { save(tag, it)}
    )
}

private fun <T>Call<T>.enqueueWithCache(
    onSuccess: (T) -> Unit,
    onError: (Throwable) -> Unit = {},
    storeData: (T) -> Unit
) {
    enqueue(
        object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if(response.isSuccessful){
                    onSuccess(response.body() as T)
                    storeData(response.body() as T)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t)
            }
        }
    )
}