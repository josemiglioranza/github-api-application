package com.ze.githubrepository.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.ze.githubrepository.R
import com.ze.githubrepository.connection.Calls

class MainActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeVariables()
        callRepositories()
    }

    private fun initializeVariables(){
        recycler = findViewById(R.id.recycler_view)
    }

    private fun callRepositories(){
        val call = Calls()
        call.getRepositories(recycler, this)
    }
}