package com.ze.githubrepository.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.ze.githubrepository.R
import com.ze.githubrepository.model.Github

class GithubRepositoryView(view: View) : Fragment(R.layout.card_component_repository) {

    private val toolbar by lazy {view.findViewById<Toolbar>(R.id.toolbar)}
    private val starsRepository by lazy {view.findViewById<AppCompatTextView>(R.id.star_text)}
    private val forkRepository by lazy {view.findViewById<AppCompatTextView>(R.id.fork_text)}
    private val imgRepository by lazy {view.findViewById<AppCompatImageView>(R.id.img_repository)}
    private val txtDescription by lazy {view.findViewById<AppCompatTextView>(R.id.txt_repository_description)}

    fun setup(repository: Github){
        toolbar.title = repository.name
        starsRepository.text = repository.numberOfWatchers.toString()
        forkRepository.text = repository.numberOfForks.toString()
        txtDescription.text = repository.descricao
    }
}