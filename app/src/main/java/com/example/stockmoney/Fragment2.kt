package com.example.stockmoney

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_2.view.*
import org.json.JSONObject

class Fragment2(private val prediction: Any) : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_2, container, false)
        val tl = view.findViewById<View>(R.id.tableLayout)
        val model = prediction
        if (model is String) {
            println("abhik")
//            if (tableLayout.visibility==View.VISIBLE)
            tl.visibility = View.GONE
            view.coming_soon.text = "Coming Soon..."

        }
        if (model is JSONObject) {
            val first = model.get("1st day")
            val second = model.get("2nd day")
            val third = model.get("3rd day")
            val fourth = model.get("4th day")
            val fifth = model.get("5th day")

            view.first_day_prediction.text = first as String?
            view.second_day_prediction.text = second as String?
            view.third_day_prediction.text = third as String?
            view.fourth_day_prediction.text = fourth as String?
            view.fifth_day_prediction.text = fifth as String?

        }
        return view
    }

}