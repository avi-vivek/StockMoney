package com.example.stockmoney

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnTouchListener
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*


class homescreen : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        llProgresshome?.visibility = View.VISIBLE


        val search = findViewById<AutoCompleteTextView>(R.id.searchBarOfMain)

        val names = mapOf(
            arrayListOf("TATA MOTORS") to "TATAMOTORS.NS",
            arrayListOf("Apple Inc.") to "AAPL",
            arrayListOf("Agora, Inc.") to "API",
            arrayListOf("Netflix Inc.") to "NFLX",
            arrayListOf("Tata Steel Limited") to "TATASTEEL.NS",
            arrayListOf("Microsoft Corporation") to "MSFT",
            arrayListOf("AMAZON") to "AMZN",
            arrayListOf("Alphabet Inc.") to "GOOG",
            arrayListOf("Facebook") to "FB",
            arrayListOf("Alibaba Group") to "BABA",
            arrayListOf("Reliance Industries Limited") to "RELIANCE.NS"
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

    override fun onBackPressed() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Are you sure")
        builder.setMessage("Do you Want to Logout?")
        builder.setPositiveButton("YES") { dialogInterface: DialogInterface, i: Int ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("NO") { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.cancel()
        }
        builder.show()
    }
}