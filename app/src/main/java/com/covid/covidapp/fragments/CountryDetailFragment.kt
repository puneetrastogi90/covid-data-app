package com.covid.covidapp.fragments

import android.os.Bundle
import android.view.View
import com.covid.covidapp.R
import com.covid.covidapp.base.BaseFragment

private const val COUNTRY_CODE = "COUNTRY_CODE"

class CountryDetailFragment : BaseFragment() {
    private var param1: String? = null


    override fun defineLayoutResources(): Int {
        return R.layout.fragment_country_detail
    }

    override fun initializeComponent(view: View) {
        arguments?.let {
            param1 = it.getString(COUNTRY_CODE)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String) =
            CountryDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(COUNTRY_CODE, param1)
                }
            }
    }
}