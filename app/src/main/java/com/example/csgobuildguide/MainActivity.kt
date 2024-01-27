package com.example.csgobuildguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.csgobuildguide.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val counterTerroristFragment = CounterTerroristFragment()
        val terroristFragment = TerroristFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, counterTerroristFragment)
            addToBackStack(null)
            commit()
        }

        binding.cterrorist.setOnClickListener{
            supportFragmentManager.beginTransaction().apply{
                replace(R.id.frameLayout, counterTerroristFragment)
                commit()
            }
        }
        binding.terrorist.setOnClickListener{
            supportFragmentManager.beginTransaction().apply{
                replace(R.id.frameLayout, terroristFragment)
                commit()
            }
        }
    }
}