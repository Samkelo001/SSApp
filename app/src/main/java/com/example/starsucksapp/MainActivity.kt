package com.example.starsucksapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.starsucksapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.idSb1.setOnClickListener(this)
        binding.idSb2.setOnClickListener(this)
        binding.idSb3.setOnClickListener (this)
        binding.idSb4.setOnClickListener(this)
        binding.idSb5.setOnClickListener(this)
        binding.idSb6.setOnClickListener(this)

        /*
        binding.idSb1.setOnClickListener {
            Toast.makeText(this, "this is a toast", Toast.LENGTH_SHORT).show()

        }*/

        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }

    override fun onClick(v : View?) {
        when(v?.id){
            R.id.id_sb1 -> Toast.makeText(this,"MMM Say Latte",Toast.LENGTH_SHORT).show()
        }
    }
}
