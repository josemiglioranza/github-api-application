package com.ze.githubrepository.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ze.githubrepository.R
import com.ze.githubrepository.adapters.GithubRepositoryAdapter
import com.ze.githubrepository.connection.CallsViewModel
import com.ze.githubrepository.lifecycle.ApplicationLogger
import com.ze.githubrepository.model.Github

class MainActivity : Fragment(R.layout.activity_main) {

    private lateinit var recycler: RecyclerView
    private val viewModel by lazy { ViewModelProvider(this).get(CallsViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeVariables(view)
        callRepositories()

        lifecycle.addObserver(ApplicationLogger())
    }

    private fun initializeVariables(view: View) {
        recycler = view.findViewById(R.id.recycler_view)
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun callRepositories() {
        viewModel.fetchRepositories()
        viewModel.githubReponse.observe(viewLifecycleOwner) {
            recycler.adapter = GithubRepositoryAdapter(it) {

            }
        }
    }
}