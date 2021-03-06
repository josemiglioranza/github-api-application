package com.ze.githubrepository.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ze.githubrepository.R
import com.ze.githubrepository.model.RepositoryModel
import com.ze.githubrepository.ui.GithubRepositoryNameView

const val WITH_JAVA = R.layout.card_component_owner
const val WITHOUT_JAVA = R.layout.card_repository_name

class GithubRepositoryAdapter(
    githubList: List<RepositoryModel.Github>,
    private val onClick : (RepositoryModel.Github) -> Unit
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
            ViewHolderRepository(GithubRepositoryNameView(parent.context))
        } else {
            ViewHolderOwner(parent.inflate(R.layout.card_component_owner))
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderRepository -> holder.bind((listJavaItems[position] as RepositoryViewType).repository, onClick)
            is ViewHolderOwner -> holder.bind((listJavaItems[position] as JavaViewType).repository)
        }
    }

    override fun getItemCount(): Int = listJavaItems.size

    override fun getItemViewType(position: Int) = listJavaItems[position].viewType

    class ViewHolderRepository(private val view: GithubRepositoryNameView) : RecyclerView.ViewHolder(view) {
        fun bind(githubItem: RepositoryModel.Github, onClick: (RepositoryModel.Github) -> Unit) {
            view.setupNameRepositoryView(githubItem)
            view.setUpClickToDetail(githubItem){
                onClick(githubItem)
            }
        }
    }

    class ViewHolderOwner(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(githubItem: RepositoryModel.Github) {
            itemView.findViewById<TextView>(R.id.txt_owner).text = githubItem.owner.name
        }
    }
}

abstract class ViewTypeSample(val viewType: Int)
class JavaViewType(val repository: RepositoryModel.Github) : ViewTypeSample(WITH_JAVA)
class RepositoryViewType(val repository: RepositoryModel.Github) : ViewTypeSample(WITHOUT_JAVA)

fun ViewGroup.inflate(layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}


