package com.example.testegit.features.main.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testegit.R
import com.example.testegit.databinding.ItemGitBinding
import com.example.testegit.features.main.data.response.GitResponse
import com.squareup.picasso.Picasso

class GitListAdapter(var callback: (Int) -> Unit) :
    RecyclerView.Adapter<GitListViewHolder>() {

    private var list: ArrayList<GitResponse> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitListViewHolder {
        return GitListViewHolder(
            ItemGitBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<GitResponse>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: GitListViewHolder, position: Int) {
        holder.bind(list[position], position)
        holder.itemView.setOnClickListener {
            callback.invoke(position)
        }
    }

    override fun getItemCount() = list.size
}

class GitListViewHolder(
    private val view: ItemGitBinding
) : RecyclerView.ViewHolder(view.root) {

    fun bind(data: GitResponse, position: Int) {
        view.apply {
            name.text = data.name
            owner.text = view.root.context.getString(R.string.owner, data.owner?.login)
            stars.text = view.root.context.getString(R.string.stars_count, data.stars)
            forks.text = view.root.context.getString(R.string.forks_count, data.forks)

            if(data.owner?.avatar?.isNotEmpty() == true)
                Picasso.get().load(data.owner.avatar).placeholder(R.drawable.loading).into(image)
        }
    }
}

