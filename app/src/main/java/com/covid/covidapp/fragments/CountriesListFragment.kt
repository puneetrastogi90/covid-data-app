package com.covid.covidapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.covid.covidapp.R
import com.covid.covidapp.base.BaseFragment


/**
 * A simple [Fragment] subclass.
 * Use the [CountriesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CountriesListFragment : BaseFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun defineLayoutResources(): Int {
        return R.layout.fragment_countries_list
    }

    override fun initializeComponent(view: View) {
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            CountriesListFragment()
    }
}