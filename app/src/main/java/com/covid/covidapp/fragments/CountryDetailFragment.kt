package com.covid.covidapp.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.covid.covidapp.R
import com.covid.covidapp.appComponent
import com.covid.covidapp.base.BaseFragment
import com.covid.covidapp.data.CountryCovidDetailModel
import com.covid.covidapp.di.CovidDataComponent
import com.covid.covidapp.di.DaggerCovidDataComponent
import com.covid.covidapp.di.ViewModelFactory
import com.covid.covidapp.utils.Utils
import com.covid.covidapp.utils.showToast
import com.covid.covidapp.viewmodels.CountryDetailViewModel
import kotlinx.android.synthetic.main.fragment_country_detail.*
import javax.inject.Inject

private const val COUNTRY_CODE = "COUNTRY_CODE"

class CountryDetailFragment : BaseFragment() {
    private var countryCode: String? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val covidDataComponent: CovidDataComponent by lazy {
        DaggerCovidDataComponent.builder().appComponent(this.appComponent()).build()
    }

    val viewModel: CountryDetailViewModel by lazy {
        ViewModelProvider(viewModelStore, viewModelFactory).get(CountryDetailViewModel::class.java)
    }

    override fun defineLayoutResources(): Int {
        return R.layout.fragment_country_detail
    }

    override fun initializeComponent(view: View) {
        covidDataComponent.inject(this)
        arguments?.let {
            countryCode = it.getString(COUNTRY_CODE)
        }
        viewModel.getCountryCovidData(countryCode!!)
        viewModel.uiState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is CountryDetailViewModel.LoadingState.Loading -> (requireActivity() as AppCompatActivity).showToast(
                    "Loading Detail for country Code: ${countryCode}"
                )
                is CountryDetailViewModel.LoadingState.Success -> {
                    (requireActivity() as AppCompatActivity).showToast(
                        "Loading Successful."
                    )
                }
                is CountryDetailViewModel.LoadingState.Error -> (requireActivity() as AppCompatActivity).showToast(
                    it.msg
                )
            }

        })

        viewModel.countryCovidDetailLiveData.observe(viewLifecycleOwner, Observer {
            initializeUi(it!!)
        })
    }

    private fun initializeUi(countryCovidDetailModel: CountryCovidDetailModel) {
        main_heading.text = "${countryCovidDetailModel!!.get(0).country} Data"
        confirmed_textview.text =
            Utils.getCommaSeparatedNumber(countryCovidDetailModel!!.get(0).confirmed!!)
        active_textview.text =
            Utils.getCommaSeparatedNumber(countryCovidDetailModel!!.get(0).critical!!)
        recovered_textview.text =
            Utils.getCommaSeparatedNumber(countryCovidDetailModel!!.get(0).recovered!!)
        deceased_textview.text =
            Utils.getCommaSeparatedNumber(countryCovidDetailModel!!.get(0).deaths!!)
        updated_time_textview.text =
            "Updated On ${Utils.getReadableDateString(countryCovidDetailModel.get(0).lastUpdate)}"

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