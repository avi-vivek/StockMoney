package com.example.stockmoney

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyAdapter(
    fm: FragmentManager,
    private val list: ArrayList<MyModel>,
    private val prediction: Any
) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return Fragment1(list = list)
            }
            1 -> {
                return Fragment2(prediction = prediction)
            }
            else -> {
                return Fragment1(list = list)
            }
        }
    }
}