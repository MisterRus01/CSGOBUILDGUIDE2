package com.example.csgobuildguide

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapter2(private val gunList: List<GunDetails>, val textColor: String) :
    RecyclerView.Adapter<RecyclerAdapter2.GunViewHolderDetailed>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GunViewHolderDetailed {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_guns_detailed, parent, false)
        return GunViewHolderDetailed(view)
    }

    override fun onBindViewHolder(holder: GunViewHolderDetailed, position: Int) {
        val gun = gunList[position]

        Glide.with(holder.itemView.context)
            .load(gun.photoPath)
            .into(holder.imageViewPhoto)

        holder.textViewName.setTextColor(Color.parseColor(textColor))
        holder.textViewPrice.setTextColor(Color.parseColor(textColor))
        holder.textViewAdd.setTextColor(Color.parseColor(textColor))
        holder.textViewName.text = gun.gunName
        holder.textViewPrice.text = gun.gunPrice

    }

    override fun getItemCount(): Int {
        return gunList.size
    }

    class GunViewHolderDetailed(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewPhoto: ImageView = itemView.findViewById(R.id.detailedViewHolder)
        val textViewName: TextView = itemView.findViewById(R.id.gunName)
        val textViewPrice: TextView=itemView.findViewById(R.id.gunPrice)
        val textViewAdd: TextView = itemView.findViewById(R.id.textView)

    }
}