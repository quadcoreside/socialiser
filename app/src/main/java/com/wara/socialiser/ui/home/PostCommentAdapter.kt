package com.wara.socialiser.ui.post

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.wara.socialiser.R
import com.wara.socialiser.data.response.Post
import com.wara.socialiser.data.response.PostComment
import com.wara.socialiser.ui.home.PostAdapater

class PostCommentAdapater(private var dataSet: List<PostComment>) : RecyclerView.Adapter<PostCommentAdapater.PostCommentListViewHolder>() {

    class PostCommentListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView
        val descriptionTextView: TextView

        init {
            titleTextView = view.findViewById(R.id.title)
            descriptionTextView = view.findViewById(R.id.description)
        }

        var itemClick: ((PostComment) -> Unit)? = null
        var onItemLongPress: ((PostComment) -> Unit)? = null

        fun bindView(postComment: PostComment) {
            titleTextView.text = postComment.name;
            descriptionTextView.text = postComment.body;

            //For Handling RecyclerView Item Click
            itemView?.setOnClickListener {
                //invoke() function will pass the value to receiver function.
                itemClick?.invoke(postComment)
            }
        }

    }

    fun updateList(list: List<PostComment>){
        dataSet = list
        notifyDataSetChanged()
    }
    private var context: Context? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PostCommentListViewHolder {
        /*
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.media_item, viewGroup, false)
        */

        return PostCommentListViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.media_item, viewGroup, false)).apply {
            itemClick = { post ->
                //this.itemClick?.invoke(post)
            }
            onItemLongPress = { post ->
                //this.onItemLongPress?.invoke(post)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: PostCommentListViewHolder, position: Int) {
        val postCommentViewHolder = viewHolder as PostCommentListViewHolder
        postCommentViewHolder.bindView(dataSet[position])
    }

    private val limit = 20
    override fun getItemCount(): Int {
        if(dataSet.size > limit){
            return limit;
        } else {
            return dataSet.size;
        }
    }
}