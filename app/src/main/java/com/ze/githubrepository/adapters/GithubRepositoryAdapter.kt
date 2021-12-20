package com.ze.githubrepository.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ze.githubrepository.R
import com.ze.githubrepository.model.Github
import com.ze.githubrepository.model.Items
import com.ze.githubrepository.ui.GithubRepositoryNameView
import com.ze.githubrepository.ui.GithubRepositoryView

const val WITH_JAVA = R.layout.card_component_owner
const val WITHOUT_JAVA = R.layout.card_repository_name

class GithubRepositoryAdapter(
    private val githubList: List<Github>,
    private val onClick : (Github) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listJavaItems = mutableListOf<ViewTypeSample>()

    init {
        githubList.forEach {
            listJavaItems.add(RepositoryViewType(it))

            if (it.name.contains("java", ignoreCase = true)) {
                listJavaItems.add(JavaViewType(it))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == WITHOUT_JAVA) {
            ViewHolderRepository(GithubRepositoryNameView(parent.context), onClick)
        } else {
            ViewHolderOwner(parent.inflate(R.layout.card_component_owner))
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderRepository -> holder.bind((listJavaItems[position] as RepositoryViewType).repository)
            is ViewHolderOwner -> holder.bind((listJavaItems[position] as JavaViewType).repository)
        }
    }

    override fun getItemCount(): Int = listJavaItems.size

    override fun getItemViewType(position: Int) = listJavaItems[position].viewType

    class ViewHolderRepository(private val view: GithubRepositoryNameView, private val onClick: (Github) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(githubItem: Github) {
            view.setupNameRepositoryView(githubItem)
            view.setUpClickToDetail {
                onClick(githubItem)
            }
        }
    }

    class ViewHolderOwner(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(githubItem: Github) {
            itemView.findViewById<TextView>(R.id.txt_owner).text = githubItem.owner.name
        }
    }
}

abstract class ViewTypeSample(val viewType: Int)
class JavaViewType(val repository: Github) : ViewTypeSample(WITH_JAVA)
class RepositoryViewType(val repository: Github) : ViewTypeSample(WITHOUT_JAVA)

fun ViewGroup.inflate(layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}


