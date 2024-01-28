package com.example.csgobuildguide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapter2(private val personsList: List<Persons>) :
    RecyclerView.Adapter<RecyclerAdapter2.PersonsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_counter_terrorist, parent, false)
        return PersonsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonsViewHolder, position: Int) {
        val person = personsList[position]

        Glide.with(holder.itemView.context)
            .load(person.photoUrl)
            .placeholder(R.drawable.image)
            .into(holder.imageViewPhoto)

        holder.textViewName.text = person.name
        holder.textViewBirthday.text = person.birthday
        holder.textViewAddress.text = person.address
    }

    override fun getItemCount(): Int {
        return personsList.size
    }

    class PersonsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewPhoto: ImageView = itemView.findViewById(R.id.imageViewPhoto)
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewBirthday: TextView = itemView.findViewById(R.id.textViewBirthday)
        val textViewAddress: TextView = itemView.findViewById(R.id.textViewAddress)
    }
}