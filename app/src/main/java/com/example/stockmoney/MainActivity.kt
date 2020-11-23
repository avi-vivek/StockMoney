package com.example.stockmoney

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun startGuest(view: View) {
        val intent = Intent(this, homescreen::class.java)
        startActivity(intent)
        finish()
    }

    fun openSignUp(view: View) {
        val intent = Intent(this, SignUp::class.java)
        startActivity(intent)
    }

    fun openLogin(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

}