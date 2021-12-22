package com.ze.githubrepository.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ze.githubrepository.R
import com.ze.githubrepository.model.RepositoryModel

class GithubRepositoryView() : Fragment(R.layout.card_component_repository) {

    private val navArgs: GithubRepositoryViewArgs by navArgs()

    private lateinit var toolbar : Toolbar
    private lateinit var starsRepository : AppCompatTextView
    private lateinit var forkRepository : AppCompatTextView
    private lateinit var imgRepository : AppCompatImageView
    private lateinit var txtDescription : AppCompatTextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeVariables(view)
        setup(navArgs.githubItem)
    }

    private fun initializeVariables(view: View) {
        toolbar = view.findViewById(R.id.toolbar)
        starsRepository = view.findViewById(R.id.star_text)
        forkRepository = view.findViewById(R.id.fork_text)
        imgRepository = view.findViewById(R.id.img_repository)
        txtDescription = view.findViewById(R.id.txt_repository_description)
    }

    fun setup(repository: RepositoryModel.Github){
        toolbar.title = repository.name
        starsRepository.text = repository.numberOfWatchers.toString()
        forkRepository.text = repository.numberOfForks.toString()
        txtDescription.text = repository.descricao
    }
}