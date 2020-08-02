package com.covid.covidapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.covid.covidapp.R
import com.covid.covidapp.appComponent
import com.covid.covidapp.base.BaseFragment
import com.covid.covidapp.data.WorldReportModel
import com.covid.covidapp.di.CovidDataComponent
import com.covid.covidapp.di.DaggerCovidDataComponent
import com.covid.covidapp.di.ViewModelFactory
import com.covid.covidapp.utils.Utils
import com.covid.covidapp.utils.addFragment
import com.covid.covidapp.utils.showToast
import com.covid.covidapp.viewmodels.CovidViewModel
import kotlinx.android.synthetic.main.fragment_world_report.*
import okhttp3.internal.Util
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [WorldReportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorldReportFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val covidDataComponent: CovidDataComponent by lazy {
        DaggerCovidDataComponent.builder().appComponent(this.appComponent()).build()
    }

    val viewModel: CovidViewModel by lazy {
        ViewModelProvider(viewModelStore, viewModelFactory).get(CovidViewModel::class.java)
    }

    override fun defineLayoutResources(): Int {
        return R.layout.fragment_world_report
    }

    override fun initializeComponent(view: View) {
        covidDataComponent.inject(this)
        viewModel.getWorldCovidData()
        searchBycountryCardView.setOnClickListener(
            View.OnClickListener {
                addFragment(R.id.container, this, CountriesListFragment.newInstance(), true)
            }
        )
        viewModel.uiState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is CovidViewModel.LoadingState.Loading -> (requireActivity() as AppCompatActivity).showToast(
                    "Loading World Covid Data"
                )
                is CovidViewModel.LoadingState.Success -> {
                    (requireActivity() as AppCompatActivity).showToast(
                        "Loading Successful."
                    )
                }
                is CovidViewModel.LoadingState.Error -> (requireActivity() as AppCompatActivity).showToast(
                    it.msg
                )
            }

        })

        viewModel.worldCovidDataLiveData.observe(viewLifecycleOwner, Observer {
            initializeUi(it!!)
        })
    }

    private fun initializeUi(worldReportModel: WorldReportModel) {
        confirmed_textview.text =
            Utils.getCommaSeparatedNumber(worldReportModel!!.get(0).confirmed!!)
        active_textview.text = Utils.getCommaSeparatedNumber(worldReportModel!!.get(0).critical!!)
        recovered_textview.text =
            Utils.getCommaSeparatedNumber(worldReportModel!!.get(0).recovered!!)
        deceased_textview.text = Utils.getCommaSeparatedNumber(worldReportModel!!.get(0).deaths!!)
        updated_time_textview.text =
            "Updated On ${Utils.getReadableDateString(worldReportModel.get(0).lastUpdate)}"

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            WorldReportFragment()

        val TAG = "WorldReportFragment"
    }
}