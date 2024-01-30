package com.example.csgobuildguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.csgobuildguide.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

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
        binding.logout.setOnClickListener{
            FirebaseAuth.getInstance().signOut();
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
            finish()
        }
        binding.myprofile.setOnClickListener{
            val intent = Intent(this, MyProfileActivity::class.java)
            startActivity(intent)
        }
    }
}