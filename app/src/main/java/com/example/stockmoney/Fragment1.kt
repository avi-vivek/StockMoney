package com.example.stockmoney

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_1.view.*


class Fragment1(private val list: ArrayList<MyModel>) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)
        val model = list[0]
        val name = model.name
        val website = model.website
        val industry = model.industry
        val employee = model.employee
        val description = model.description

        view.company_name.text = name
        view.website_name.text = website
        view.industry_name.text = industry
        view.employee_count.text = employee
        view.company_discription.text = description


        return view
    }

}