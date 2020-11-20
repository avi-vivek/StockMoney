package com.example.stockmoney

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.cuberto.liquid_swipe.LiquidPager


class Guest : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)

        val pager = findViewById<LiquidPager>(R.id.pager)
        pager.adapter = Adapter(supportFragmentManager)


        val search = findViewById<AutoCompleteTextView>(R.id.searchBarOfMain)

//        val names = arrayListOf(
//            "Android",
//            "java",
//            "TATA",
//            "RELIANCE",
//            "BIRLA",
//            "TATA motors",
//            "TATA STEEL",
//            "JIO",
//            "ACC"
//        )
//        var names: HashMap<ArrayList<Any>,String> = HashMap<ArrayList<Any>,String>()
//        names.put(arrayListOf("TATA MOTORS"), "TATAMOTORS.NS")
//        names.put(arrayListOf("JIO"), "REL.JIO")
        val names = mapOf(
            arrayListOf("TATA MOTORS") to "TATAMOTORS.NS",
            arrayListOf("Apple Inc.") to "AAPL",
            arrayListOf("Agora, Inc.") to "API",
            arrayListOf("Netflix, Inc.") to "NFLX",
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

//        Log.d("Check","${}")

        search.onItemClickListener = OnItemClickListener { parent, view, position, id ->
//            Log.d(
//                "Check",
//                search.text.toString()
//            )
            val key = search.text.toString()
            println(key)
            println(names[arrayListOf(key)])
            val cid = names[arrayListOf(key)]
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("cid", cid)
            startActivity(intent)
        }

    }
}
