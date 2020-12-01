package com.example.stockmoney

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import kotlinx.android.synthetic.main.activity_main2.*
import org.json.JSONObject
import java.io.IOException

class MainActivity2 : AppCompatActivity() {

    private lateinit var myModelList: ArrayList<MyModel>

    override fun onCreate(savedInstanceState: Bundle?) {

//        llProgresshome?.visibility= View.VISIBLE
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val bundle = intent.extras
        val cid = bundle?.get("cid")
        val name = bundle?.get("name").toString()
        api(cid, name)
    }

    private fun api(cid: Any?, name: String) {
        llProgresshome?.visibility = View.VISIBLE
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )

        val url = "https://stockprediction-api.herokuapp.com/?cid=$cid"
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        val test = client.newCall(request).enqueue(object : Callback {
            override fun onResponse(response: Response?) {
                println("api response")
                val body = response?.body()?.string()

                val obj = JSONObject(body!!)
                val company = obj.getJSONArray("Company Info")
                val prediction = obj.getJSONArray("Prediction")
                val details = company.getJSONObject(0)
                val prediction_details = prediction.get(0)
                val employee = details.getString("Employee")
                val description = details.getString("Discription")
                val industry = details.getString("Industry")
                val website = details.getString("Website").toString()
                runOnUiThread {
                    load_cards(name, website, industry, employee, description, prediction_details)
                }

            }

            override fun onFailure(request: Request?, e: IOException?) {
                println(e)
//                website = null.toString()
                Log.d("check", "FAIL")
                Toast.makeText(
                    this@MainActivity2,
                    "Plese check your internet connection",
                    Toast.LENGTH_SHORT
                )
                    .show()

            }

        })
    }

    private fun load_cards(
        name: String,
        website: String,
        industry: String,
        employee: String,
        description: String,
        prediction_details: Any
    ) {
        llProgresshome?.visibility = View.GONE
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        myModelList = ArrayList()
        myModelList.add(
            MyModel(
                name = name,
                website = website,
                industry = industry,
                employee = employee,
                description = description
            )
        )

        viewPager.adapter = MyAdapter(supportFragmentManager, myModelList, prediction_details)
        viewPager.setPadding(50, 0, 50, 0)
    }
}

