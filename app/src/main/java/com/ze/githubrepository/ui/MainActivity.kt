package com.ze.githubrepository.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ze.githubrepository.R
import com.ze.githubrepository.adapters.GithubRepositoryAdapter
import com.ze.githubrepository.connection.CallsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private val viewModel by lazy { ViewModelProvider(this).get(CallsViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeVariables()
        callRepositories()
    }

    private fun initializeVariables(){
        recycler = findViewById(R.id.recycler_view)
        recycler.layoutManager = LinearLayoutManager(this)
    }

    private fun callRepositories(){
        viewModel.fetchRepositories()
        viewModel.githubReponse.observe(this) {
            recycler.adapter = GithubRepositoryAdapter(it)
        }
    }
}