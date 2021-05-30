package com.wara.socialiser.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.wara.socialiser.R
import com.wara.socialiser.data.response.Post


class PostAdapater(private var dataSet: List<Post>) : RecyclerView.Adapter<PostAdapater.PostListViewHolder>() {
    val EXTRA_MESSAGE = "com.wara.socialiser.MESSAGE"

    class PostListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView
        val descriptionTextView: TextView
        private var image: ImageView? = null

        init {
            titleTextView = view.findViewById(R.id.title)
            descriptionTextView = view.findViewById(R.id.description)
            image = view.findViewById(R.id.img)
        }

        fun getImage(): ImageView? {
            return image
        }

        var itemClick: ((Post) -> Unit)? = null
        var onItemLongPress: ((Post) -> Unit)? = null

        fun bindView(post: Post) {
            titleTextView.text = post.title;
            descriptionTextView.text = post.body;

            /* Chargement de l'image avec Glide */
            /* dataSet[position].thumbnailUrl on affiche la meme image pour tout les posts */
            Glide.with(itemView.context)
                .load("https://www.esiea.fr/wp-content/uploads/2016/04/Logo-ESIEA.jpg").centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(getImage());

            //For Handling RecyclerView Item Click
            itemView?.setOnClickListener {
                //invoke() function will pass the value to receiver function.
                itemClick?.invoke(post)
            }

            //For handling RecyclerView Item Long Click
            /*itemView?.setOnLongClickListener {
                onItemLongPress?.invoke(post)
                return@setOnLongClickListener true
            }*/
        }

    }

    fun updateList(list: List<Post>){
        dataSet = list
        notifyDataSetChanged()
    }
    private var context: Context? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PostListViewHolder {
        /*
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.media_item, viewGroup, false)
        */

        return PostListViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.media_item, viewGroup, false)).apply {
            itemClick = { post ->
                //this.itemClick?.invoke(post)

                Log.d("TAG", "id = " + post.id)

                //val intent = Intent(viewGroup.context, PostViewActivity::class.java)

                //transaction.replace(R.id.fragmentPost, fragment)

                /*intent.putExtra("post_id", post.id)
                viewGroup.context.startActivity(intent)*/
            }
            onItemLongPress = { post ->
                //this.onItemLongPress?.invoke(post)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: PostListViewHolder, position: Int) {
        val postViewHolder = viewHolder as PostListViewHolder
        postViewHolder.bindView(dataSet[position])
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