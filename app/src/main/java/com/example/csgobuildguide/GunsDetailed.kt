package com.example.csgobuildguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class GunsDetailed : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_counter_terrorist)

        val gunInfo = intent.getSerializableExtra("gunInfo") as? GunInfo
        val textColor = intent.getStringExtra("textColor")

        val recyclerView: RecyclerView = findViewById(R.id.detaildGunFragment)
        val pathString: String

        if(textColor=="#0000ff"){
            pathString="CounterTerroristGuns/"+gunInfo?.gunType?.toLowerCase()
        }else{
            pathString="TerroristGuns/"+gunInfo?.gunType?.toLowerCase()
        }
        Log.e("myTag", pathString)
        getGunsInfo(recyclerView, pathString)

    }
    private fun getGunsInfo(recyclerView: RecyclerView, pathString: String) {
        val gunList = mutableListOf<GunDetails>()
        val storageReference = FirebaseStorage.getInstance().reference.child(pathString)
        storageReference.listAll().addOnCompleteListener { result ->
            if (result.isSuccessful) {
                val items: List<StorageReference> = result.result!!.items
                var downloadCount = 0 // Counter to track completed download operations

                items.forEachIndexed { index, item ->
                    val itemName = removeLast4Chars(item.name)
                    val gunPrice = itemName.takeLast(5)
                    item.downloadUrl.addOnSuccessListener { uri ->
                        gunList.add(GunDetails(uri.toString(), itemName.uppercase().dropLast(5), gunPrice) )
                    }.addOnCompleteListener {
                        downloadCount++
                        if (downloadCount == items.size) {
                            val extraString = intent.getStringExtra("textColor").toString()
                            recyclerView.adapter = RecyclerAdapter2(gunList, extraString, this)
                            recyclerView.layoutManager = LinearLayoutManager(this)
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