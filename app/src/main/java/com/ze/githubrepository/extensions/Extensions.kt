package com.ze.githubrepository.extensions

import com.ze.githubrepository.cache.Cache
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

    enqueue(
        object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        cache[tag] = it
                        onSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t)
            }
        }
    )
}