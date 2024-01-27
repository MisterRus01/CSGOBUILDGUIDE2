package com.example.csgobuildguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class CounterTerroristFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

        ): View? {

        return inflater.inflate(R.layout.fragment_counter_terrorist, container, false)
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
                            recyclerView.adapter = RecyclerAdapter(gunList)
                            recyclerView.layoutManager = LinearLayoutManager(context)
                        }
                    }
                }
            }
        }
    }
    private fun removeLast4Chars(inputString: String): String {
        if (inputString.length <= 4) {
            return inputString
        } else {
            return inputString.substring(0, inputString.length - 4)
        }
    }

}