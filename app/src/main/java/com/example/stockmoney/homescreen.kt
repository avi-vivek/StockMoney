package com.example.stockmoney

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View.OnTouchListener
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity


class homescreen : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val search = findViewById<AutoCompleteTextView>(R.id.searchBarOfMain)

        val names = mapOf(
            arrayListOf("TATA MOTORS") to "TATAMOTORS.NS",
            arrayListOf("Apple Inc.") to "AAPL",
            arrayListOf("Agora, Inc.") to "API",
            arrayListOf("Netflix Inc.") to "NFLX",
            arrayListOf("ICICI Bank Limited") to "ICICIBANK.NS",
            arrayListOf("Tata Steel Limited") to "TATASTEEL.NS",
            arrayListOf("Microsoft Corporation") to "MSFT",
            arrayListOf("AMAZON") to "AMZN",
            arrayListOf("Alphabet Inc.") to "GOOG",
            arrayListOf("Facebook") to "FB",
            arrayListOf("Alibaba Group") to "BABA",
        )
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, names.keys.flatten())

        search.setAdapter(adapter)

        search.setOnTouchListener(OnTouchListener { v, event ->
            search.showDropDown()
            false
        })


        search.onItemClickListener = OnItemClickListener { parent, view, position, id ->
//            Log.d(
//                "Check",
//                search.text.toString()
//            )
            val key = search.text.toString()
            println("famos")
            println(key)
            println(names[arrayListOf(key)])
            val cid = names[arrayListOf(key)]
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("cid", cid)
            intent.putExtra("name", key)
            startActivity(intent)
        }
    }
}