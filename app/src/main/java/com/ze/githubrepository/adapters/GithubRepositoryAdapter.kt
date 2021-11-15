package com.ze.githubrepository.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ze.githubrepository.R
import com.ze.githubrepository.model.Github
import com.ze.githubrepository.model.Items

const val WITH_JAVA = R.layout.card_component_owner
const val WITHOUT_JAVA = R.layout.card_component_repository

class GithubRepositoryAdapter(private val githubList: List<Github>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemRepoViewHolder =
            LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.card_component_repository -> ViewHolderRepository(itemRepoViewHolder)
            R.layout.card_component_owner -> ViewHolderOwner(itemRepoViewHolder)
            else -> ViewHolderRepository(itemRepoViewHolder)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderRepository -> holder.bind(githubList[position])
            is ViewHolderOwner -> holder.bind(githubList[position])
        }
    }

    override fun getItemCount(): Int = githubList.size

    override fun getItemViewType(position: Int): Int {
        return if (githubList[position].name.contains("Java", true)) {
            WITH_JAVA
        } else {
            WITHOUT_JAVA
        }
    }

    class ViewHolderRepository(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(githubItem: Github) {
            itemView.findViewById<TextView>(R.id.txt_name_repository).text =
                "${githubItem.name} java"
            itemView.findViewById<TextView>(R.id.star_text).text =
                githubItem.numberOfForks.toString()
            itemView.findViewById<TextView>(R.id.fork_text).text =
                githubItem.numberOfWatchers.toString()
            itemView.findViewById<TextView>(R.id.txt_repository_description).text =
                githubItem.descricao
        }
    }

    class ViewHolderOwner(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(githubItem: Github) {
            itemView.findViewById<TextView>(R.id.txt_name_owner).text = githubItem.owner.name
        }
    }
}
