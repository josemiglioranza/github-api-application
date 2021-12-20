package com.ze.githubrepository.ui

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ze.githubrepository.R
import com.ze.githubrepository.model.Github

class GithubRepositoryNameView(context: Context) : ConstraintLayout(context) {

    private val nameRepository by lazy { findViewById<TextView>(R.id.txt_repository_name)}
    private val linkToDatails by lazy { findViewById<TextView>(R.id.link_to_details) }

    init {
        inflate(context, R.layout.card_repository_name, this)
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        setPadding(16, 40, 10, 0)
    }

    fun setupNameRepositoryView(github: Github) {
        nameRepository.text = github.name
    }

    fun setUpClickToDetail(onClick: () -> Unit){
        linkToDatails.setOnClickListener {
            onClick()
        }
    }
}