package com.ze.githubrepository.ui

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ze.githubrepository.R
import com.ze.githubrepository.model.Github

class GithubRepositoryView(context: Context) : ConstraintLayout(context) {

    private val nameRepository by lazy {findViewById<AppCompatTextView>(R.id.txt_name_repository)}
    private val starsRepository by lazy {findViewById<AppCompatTextView>(R.id.star_text)}
    private val forkRepository by lazy {findViewById<AppCompatTextView>(R.id.fork_text)}
    private val imgRepository by lazy {findViewById<AppCompatImageView>(R.id.img_repository)}
    private val txtDescription by lazy {findViewById<AppCompatTextView>(R.id.txt_repository_description)}

    init {
        inflate(context, R.layout.card_component_repository, this)
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        setPadding(3, 40, 3, 40)
    }

    fun setup(repository: Github){
        nameRepository.text = repository.name
        starsRepository.text = repository.numberOfWatchers.toString()
        forkRepository.text = repository.numberOfForks.toString()
        txtDescription.text = repository.descricao
    }
}