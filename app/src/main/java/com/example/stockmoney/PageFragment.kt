package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stockmoney.R

class PageFragment : Fragment() {
    var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments?.getInt("POSITION")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutId = if (position == 1) {
            R.layout.fragment_a
        } else if (position == 2) {
            R.layout.fragment_b
        } else R.layout.fragement_company_details
        return layoutInflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (position) {
            1 -> return
            2 -> view.setBackgroundColor(
                Color.rgb(
                    (0.3647058904 * 255).toInt(),
                    (0.06666667014 * 255).toInt(),
                    (0.9686274529 * 255).toInt()
                )

            )
        }

    }
}