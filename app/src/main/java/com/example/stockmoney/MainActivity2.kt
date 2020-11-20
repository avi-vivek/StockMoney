package com.example.stockmoney

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import java.io.IOException


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val bundle = intent.extras
        val cid = bundle?.get("cid")


        println(cid)
        val textView: TextView = findViewById<TextView>(R.id.json_display)
        val url = "https://stockprediction-api.herokuapp.com/?cid=$cid"

        println(url)

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(response: Response?) {
                println("RUN")
                val body = response?.body()?.string()
                println(body)
                body?.let { Log.d("check", it) }
                textView.text = body
            }

            override fun onFailure(request: Request?, e: IOException?) {
                println(e)
                Log.d("check", "FAIL")
            }
        })
    }
}
