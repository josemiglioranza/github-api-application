package com.ze.githubrepository.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ze.githubrepository.R
import com.ze.githubrepository.model.Github
import com.ze.githubrepository.model.Items

class GithubRepositoryAdapter(private val githubList: List<Github>):RecyclerView.Adapter<GithubRepositoryAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemRepoViewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_component_repository, parent, false)
        return ViewHolder(itemRepoViewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(githubList[position])
    }

    override fun getItemCount(): Int = githubList.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(githubItem: Github) {
            itemView.findViewById<TextView>(R.id.txt_name_repository).text = githubItem.name
            itemView.findViewById<TextView>(R.id.star_text).text = githubItem.numberOfForks.toString()
            itemView.findViewById<TextView>(R.id.fork_text).text = githubItem.numberOfWatchers.toString()
            itemView.findViewById<TextView>(R.id.txt_repository_description).text = githubItem.descricao
        }
    }
}
