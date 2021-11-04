package com.ze.githubrepository.connection

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ze.githubrepository.adapters.GithubRepositoryAdapter
import com.ze.githubrepository.model.Github
import com.ze.githubrepository.model.Items
import com.ze.githubrepository.ui.ItemDecorator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Calls() {

    fun getRepositories(recycler: RecyclerView, context: Context) {

        val build = ApiConnection.buildService(GithubApi::class.java)
        val call = build.getRepositories()

        call.enqueue(object : Callback<Items> {
            override fun onResponse(call: Call<Items>, response: Response<Items>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        recycler.apply {
                            adapter = GithubRepositoryAdapter(it.items)
                            layoutManager = LinearLayoutManager(context)
                            addItemDecoration(
                                ItemDecorator(80)
                            )
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Items>, t: Throwable) {
                Log.e("OPA", "Deu ruim")
            }
        })
    }
}