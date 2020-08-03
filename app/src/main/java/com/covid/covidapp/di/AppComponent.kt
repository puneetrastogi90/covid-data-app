package com.covid.covidapp.di

import android.app.Application
import android.content.Context
import com.byjus.assignment.headlines.di.room.RoomModule
import com.covid.covidapp.daos.CountriesListDao
import com.covid.covidapp.daos.CountryDetailDao
import com.covid.covidapp.daos.WorldDataDao
import dagger.Component

@Component(modules = [AppModule::class, RoomModule::class])
interface AppComponent {

    fun context(): Context
    fun appContext(): Application
    fun worldDao(): WorldDataDao
    fun countriesListDao(): CountriesListDao
    fun countryDetailDao(): CountryDetailDao

}