package com.covid.covidapp.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.covid.covidapp.R
import com.covid.covidapp.base.BaseFragment
import com.covid.covidapp.utils.replaceFragment


class SplashFragment : BaseFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun defineLayoutResources(): Int {
        return R.layout.fragment_splash
    }

    override fun initializeComponent(view: View) {
        Handler().postDelayed({
            replaceFragment(
                R.id.container,
                requireActivity().supportFragmentManager,
                WorldReportFragment.newInstance(),
                true
            )
        }, 3000)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SplashFragment()
    }
}