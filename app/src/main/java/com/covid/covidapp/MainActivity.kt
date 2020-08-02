package com.covid.covidapp

import com.covid.covidapp.base.BaseActivity
import com.covid.covidapp.base.BaseFragment
import com.covid.covidapp.fragments.WorldReportFragment
import com.covid.covidapp.utils.replaceFragment

class MainActivity : BaseActivity() {
    private var currentFragment: BaseFragment = WorldReportFragment.newInstance()

    override fun defineLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun initializeComponents() {
        replaceFragment(R.id.container, supportFragmentManager, currentFragment, false)
    }
}