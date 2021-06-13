package com.vitgames.diplom.imageLoader

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vitgames.diplom.R

class GalleryAdapter(private val context: Context, private var images: ArrayList<String?>, ) :
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image)
    }
    interface PhotoListener {
        fun onPhotoClick(path: String)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.gallery_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image: String? = images[position]
        Glide.with(context).load(image).into(holder.image)
        holder.itemView.setOnClickListener {
            Toast.makeText(context, image, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}

