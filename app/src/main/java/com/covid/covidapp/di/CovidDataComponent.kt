package com.covid.covidapp.di

import com.covid.covidapp.fragments.CountriesListFragment
import com.covid.covidapp.fragments.CountryDetailFragment
import com.covid.covidapp.fragments.WorldReportFragment
import dagger.Component

@Component(
    modules = [CovidDataModule::class, ViewModelModule::class],
    dependencies = [AppComponent::class]
)
interface CovidDataComponent {

    @Component.Builder
    interface Builder {
        fun appComponent(module: AppComponent): Builder
        fun build(): CovidDataComponent
    }

    fun inject(fragment: WorldReportFragment)
    fun inject(fragment: CountriesListFragment)
    fun inject(fragment: CountryDetailFragment)
}