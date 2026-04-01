package com.example.starsucksapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.starsucksapp.databinding.ActivityOrderDetailsBinding

class OrderDetailsActivity : AppCompatActivity() {

    var order = Order()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order_details)
        val binding = ActivityOrderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //order.productName = intent.getIntExtra("order", -1).toString()
        order.productName = intent.getStringExtra("order") ?: "Unknown Product"

        binding.tvPlaceHolder.text = order.productName

        when(order.productName){
            "Soy Latte" -> binding.imageView3.setImageResource(R.drawable.sb1)
            "Chocco Frappe" -> binding.imageView3.setImageResource(R.drawable.sb1)
            "Bottled Americano" -> binding.imageView3.setImageResource(R.drawable.sb2)
        }
        binding.fabOrder.setOnClickListener {
            shareIntent(applicationContext, order.productName)
        }
        /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }
}