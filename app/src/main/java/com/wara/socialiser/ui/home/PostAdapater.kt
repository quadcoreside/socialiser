package com.wara.socialiser.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.wara.socialiser.R
import com.wara.socialiser.data.response.Album


class PostAdapater(private var dataSet: List<Album>) : RecyclerView.Adapter<PostAdapater.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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
    }
    fun updateList(list: List<Album>){
        dataSet = list
        notifyDataSetChanged()
    }
    private var context: Context? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.media_item, viewGroup, false)

        /* On recuperer le context de cette maniere */
        context = viewGroup.context;

        return ViewHolder(view)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.titleTextView.text = dataSet[position].title;
        viewHolder.descriptionTextView.text = dataSet[position].url;

        /* Chargement de l'image avec Glide */
        /* dataSet[position].thumbnailUrl */
        Glide.with(context)
            .load("https://www.esiea.fr/wp-content/uploads/2016/04/Logo-ESIEA.jpg").centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(viewHolder.getImage());
    }

    override fun getItemCount() = dataSet.size

}