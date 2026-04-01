package com.example.starsucksapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.starsucksapp.databinding.ActivityMainBinding
import com.example.starsucksapp.databinding.ActivityMainWithNavBinding
import com.example.starsucksapp.databinding.ActivityMainWithNavDrawer2Binding
import com.example.starsucksapp.databinding.ActivityMainWithNavDrawerBinding
//import android.view.MenuItem
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    var order = Order()
    private lateinit var binding: ActivityMainWithNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)


        val binding = ActivityMainWithNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.idSb1.setOnClickListener(this)
        binding.idSb2.setOnClickListener(this)
        binding.idSb3.setOnClickListener (this)
        binding.idSb4.setOnClickListener(this)
        binding.idSb5.setOnClickListener(this)
        binding.idSb6.setOnClickListener(this)

        setSupportActionBar(binding.navToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        var toggleOnOff = ActionBarDrawerToggle(
            this,
            binding.drawerLayout, binding.navToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggleOnOff)
        toggleOnOff.syncState()

        binding.navView.bringToFront()
        binding.navView.setNavigationItemSelectedListener(this)
        
    }

    override fun onClick(v : View?) {
        when(v?.id){
            R.id.id_sb1 -> order.productName = "Soy Latte"
            R.id.id_sb2 -> order.productName = "Chocco Frappe"
            R.id.id_sb3-> order.productName = "Bottled Americano"

         }
        openIntent(applicationContext, order.productName, OrderDetailsActivity::class.java)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_main -> {
                //Toast.makeText(this, "Menu is clicked", Toast.LENGTH_SHORT).show()
            }

            R.id.take_photo -> {
                openIntent(applicationContext,"",CoffeeSnapActivity::class.java)
                Toast.makeText(this, "Take photo is clicked", Toast.LENGTH_SHORT).show()
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    /*@SuppressLint("GestureBackNavigation")
    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }*/
}
class Order(){
    lateinit var productName: String
    lateinit var customerName: String
    lateinit var customerCell: String
    lateinit var orderDate: String

    constructor(pName: String) : this() {
        productName = pName
    }

    constructor(pName: String, cName: String, cCell: String,
                oDate: String) : this(pName) {
        customerName = cName
        customerCell = cCell
        orderDate = oDate
    }

}

fun openIntent(context: Context, order: String, activityToOpen: Class<*>){
    val intent = Intent(context, activityToOpen)

    intent.putExtra("order", order)

    if(context !is android.app.Activity){
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    context.startActivity(intent)
}

fun shareIntent(context: Context, order: String){
    val sendIntent = Intent()

    sendIntent.action = Intent.ACTION_SEND

    sendIntent.putExtra(Intent.EXTRA_TEXT, order)

    sendIntent.type = "text/plain"

    val shareIntent = Intent.createChooser(sendIntent, null)

    if(context !is android.app.Activity){
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(shareIntent)
}


fun shareIntent(context: Context, order: Order){
    val sendIntent = Intent()

    val shareOrderDetails = Bundle()
    shareOrderDetails.putString("productName", order.productName)
    shareOrderDetails.putString("customerName", order.customerName)
    shareOrderDetails.putString("customerCell", order.customerCell)

    sendIntent.putExtra(Intent.EXTRA_TEXT, shareOrderDetails)
    sendIntent.type = "text/plain"

    val shareIntent = Intent.createChooser(sendIntent, null)

    if(context !is android.app.Activity){
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(shareIntent)
}
