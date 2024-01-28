package com.example.csgobuildguide

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class CounterTerroristFragment : Fragment(), OnItemClickListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

        ): View? {

        return inflater.inflate(R.layout.fragment_terrorist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.RecyclerViewCounterTerrorist)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        getGunsInfo(recyclerView)
    }

    private fun getGunsInfo(recyclerView: RecyclerView) {
        val gunList = mutableListOf<GunInfo>()
        val storageReference = FirebaseStorage.getInstance().reference.child("menuCounterTerrorist")
        storageReference.listAll().addOnCompleteListener { result ->
            if (result.isSuccessful) {
                val items: List<StorageReference> = result.result!!.items
                var downloadCount = 0 // Counter to track completed download operations

                items.forEachIndexed { index, item ->
                    val itemName = removeLast4Chars(item.name)
                    item.downloadUrl.addOnSuccessListener { uri ->
                        gunList.add(GunInfo(uri.toString(), itemName.uppercase()))
                    }.addOnCompleteListener {
                        downloadCount++
                        if (downloadCount == items.size) {
                            // Set the adapter once all downloads are complete

                            val extraString = "#0000ff" // Provide the actual value for the extra string
                            recyclerView.adapter = RecyclerAdapter(gunList, extraString, this)
                            recyclerView.layoutManager = LinearLayoutManager(context)

                        }
                    }
                }
            }
        }
    }
    override fun onItemClick(gunInfo: GunInfo, textColor: String) {
        // Handle item click, e.g., open a new activity
        val intent = Intent(requireContext(), GunsDetailed::class.java)
        intent.putExtra("gunInfo", gunInfo)
        intent.putExtra("textColor", textColor)
        startActivity(intent)
    }
    private fun removeLast4Chars(inputString: String): String {
        if (inputString.length <= 4) {
            return inputString
        } else {
            return inputString.substring(0, inputString.length - 4)
        }
    }

}