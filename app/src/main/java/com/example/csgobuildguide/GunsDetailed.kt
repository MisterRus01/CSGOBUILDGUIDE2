package com.example.csgobuildguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GunsDetailed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guns_detailed)


        val gunInfo = intent.getSerializableExtra("gunInfo") as? GunInfo
        val textColor = intent.getStringExtra("textColor")

    }
}