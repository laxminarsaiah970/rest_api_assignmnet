package com.example.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.PostItemBinding

class PostAdapter (var postList: ArrayList<Posts>):RecyclerView.Adapter<PostAdapter.PostViewHolder>(){
    inner class PostViewHolder(val adapterBinding:PostItemBinding):RecyclerView.ViewHolder(adapterBinding.root)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        val binding=PostItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
    holder.adapterBinding.textViewUserId.text=postList[position].userId.toString()
        holder.adapterBinding.textViewId.text=postList[position].id.toString()
        holder.adapterBinding.textViewTitle.text=postList[position].title
        holder.adapterBinding.textViewBody.text=postList[position].body

    }

}