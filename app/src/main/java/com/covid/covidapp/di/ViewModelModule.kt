package com.covid.covidapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.covid.covidapp.viewmodels.CovidViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewSpacesViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(CovidViewModel::class)
    abstract fun bindViewSpacesViewModel(covidViewModel: CovidViewModel): ViewModel
}