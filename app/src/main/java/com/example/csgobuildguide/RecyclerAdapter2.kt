package com.example.csgobuildguide

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase



class RecyclerAdapter2(private val gunList: List<GunDetails>, val textColor: String, private val context: Context) :
    RecyclerView.Adapter<RecyclerAdapter2.GunViewHolderDetailed>() {
    private val db = Firebase.firestore
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
    private lateinit var database: DatabaseReference


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


        holder.textViewAdd.setOnClickListener {
            Log.e("DOLAZI", "Dolazi")
            Log.e("GUN", gunList[position].gunName)
            database = FirebaseDatabase.getInstance("https://cs-go-build-guide-default-rtdb.europe-west1.firebasedatabase.app").getReference(userId)
            database.child(gun.gunName).setValue(gun).addOnSuccessListener {
                Toast.makeText(context, "Successfully saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(context, "Failed to save", Toast.LENGTH_SHORT).show()
            }

        }
    }


    override fun getItemCount(): Int {
        return gunList.size
    }

    class GunViewHolderDetailed(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewPhoto: ImageView = itemView.findViewById(R.id.myProfileGunHolder)
        val textViewName: TextView = itemView.findViewById(R.id.gunNameMyProfile)
        val textViewPrice: TextView=itemView.findViewById(R.id.gunPrice)
        val textViewAdd: TextView = itemView.findViewById(R.id.textView)

    }
}