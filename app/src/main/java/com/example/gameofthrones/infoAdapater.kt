package com.example.gameofthrones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class infoAdapater( val petList : List<String>,private val names:List<String>,private val families: List<String>,private val titles: List<String>) : RecyclerView.Adapter<infoAdapater.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val charImage: ImageView
        val name : TextView
        val family: TextView
        val title: TextView


        init {
            charImage = view.findViewById(R.id.character_image)
            name = view.findViewById(R.id.name)
            family = view.findViewById(R.id.family)
            title = view.findViewById(R.id.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = names[position]
        holder.family.text = families[position]
        holder.title.text = titles[position]



        Glide.with(holder.itemView)
            .load(petList[position])
            .centerCrop()
            .into(holder.charImage)

        holder.charImage.setOnClickListener{
            Toast.makeText(holder.itemView.context,"Character at position $position clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = petList.size
}