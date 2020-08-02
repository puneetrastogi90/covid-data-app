package com.covid.covidapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.covid.covidapp.viewmodels.CountriesListViewModel
import com.covid.covidapp.viewmodels.CountryDetailViewModel
import com.covid.covidapp.viewmodels.CovidViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelsFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(CovidViewModel::class)
    abstract fun bindCovidViewModel(covidViewModel: CovidViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CountriesListViewModel::class)
    abstract fun bindCountriesListViewModel(countriesListViewModel: CountriesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CountryDetailViewModel::class)
    abstract fun bindCountryDataViewModel(countryDetailViewModel: CountryDetailViewModel): ViewModel
}