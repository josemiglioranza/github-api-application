package com.ze.githubrepository.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ze.githubrepository.R
import com.ze.githubrepository.adapters.GithubRepositoryAdapter
import com.ze.githubrepository.connection.CallsViewModel
import com.ze.githubrepository.lifecycle.ApplicationLogger

class NavigationFragment : Fragment(R.layout.navigation_fragment) {

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
        recycler.layoutManager = LinearLayoutManager(context)
    }

    private fun callRepositories() {
        viewModel.fetchRepository2()
        viewModel.githubReponse.observe(this) {
            recycler.adapter = GithubRepositoryAdapter(it) { github ->
                findNavController(this).navigate(
                    NavigationFragmentDirections.actionMainFragmentToDetailsFragment(github)
                )
            }
        }
    }
}