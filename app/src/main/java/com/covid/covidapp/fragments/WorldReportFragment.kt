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
 * Use the [WorldReportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorldReportFragment : BaseFragment(){


    override fun defineLayoutResources(): Int {
        return R.layout.fragment_world_report
    }

    override fun initializeComponent(view: View) {
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            WorldReportFragment()
    }
}