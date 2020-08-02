package com.covid.covidapp.fragments

import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.covid.covidapp.R
import com.covid.covidapp.adapters.CountriesListRecyclerViewAdapter
import com.covid.covidapp.appComponent
import com.covid.covidapp.base.BaseFragment
import com.covid.covidapp.data.CountriesListModel
import com.covid.covidapp.data.CountriesListModelItem
import com.covid.covidapp.di.CovidDataComponent
import com.covid.covidapp.di.DaggerCovidDataComponent
import com.covid.covidapp.di.ViewModelFactory
import com.covid.covidapp.utils.addFragment
import com.covid.covidapp.utils.showToast
import com.covid.covidapp.viewmodels.CountriesListViewModel
import kotlinx.android.synthetic.main.fragment_countries_list.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [CountriesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CountriesListFragment : BaseFragment(),
    CountriesListRecyclerViewAdapter.AdapterInteractionListener {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val covidDataComponent: CovidDataComponent by lazy {
        DaggerCovidDataComponent.builder().appComponent(this.appComponent()).build()
    }

    val viewModel: CountriesListViewModel by lazy {
        ViewModelProvider(viewModelStore, viewModelFactory).get(CountriesListViewModel::class.java)
    }
    private val countriesListRecyclerViewAdapter: CountriesListRecyclerViewAdapter by lazy {
        CountriesListRecyclerViewAdapter(this)
    }

    override fun defineLayoutResources(): Int {
        return R.layout.fragment_countries_list
    }

    override fun initializeComponent(view: View) {
        covidDataComponent.inject(this)
        initializeRecyclerView()
        viewModel.getCountriesList()
        viewModel.uiState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is CountriesListViewModel.LoadingState.Loading -> (requireActivity() as AppCompatActivity).showToast(
                    "Fetching Countries List"
                )
                is CountriesListViewModel.LoadingState.Success -> {
                    (requireActivity() as AppCompatActivity).showToast(
                        "Loading Successful."
                    )
                }
                is CountriesListViewModel.LoadingState.Error -> (requireActivity() as AppCompatActivity).showToast(
                    it.msg
                )
            }

        })

        viewModel.countriesListLiveData.observe(viewLifecycleOwner, Observer {
            countriesListRecyclerViewAdapter.updateList(it)
        })
    }

    private fun initializeRecyclerView() {
        countriesListRecyclerView.adapter = countriesListRecyclerViewAdapter
        countriesListRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            CountriesListFragment()

        val TAG = "CountriesListFragment"
    }

    override fun onItemClick(countriesListModelItem: CountriesListModelItem) {
        addFragment(
            R.id.container,
            this,
            CountryDetailFragment.newInstance(countriesListModelItem.alpha2code),
            true
        )
    }
}