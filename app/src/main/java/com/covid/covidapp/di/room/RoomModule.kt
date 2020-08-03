package com.byjus.assignment.headlines.di.room

import android.content.Context
import androidx.room.Room
import com.covid.covidapp.daos.CountriesListDao
import com.covid.covidapp.daos.CountryDetailDao
import com.covid.covidapp.daos.WorldDataDao
import com.covid.covidapp.database.CovidRoomDatabase
import dagger.Module
import dagger.Provides

@Module
class RoomModule() {

    @Provides
    fun provideDatabase(context: Context): CovidRoomDatabase =
        Room.databaseBuilder(context, CovidRoomDatabase::class.java, "covid.db").build()

    @Provides
    fun provideoWorldDataDao(database: CovidRoomDatabase): WorldDataDao =
        database.getWorldDataDao()

    @Provides
    fun provideCountriesListDao(database: CovidRoomDatabase): CountriesListDao =
        database.getCountriesListDao()

    @Provides
    fun providesCountryDetailDao(database: CovidRoomDatabase): CountryDetailDao =
        database.getCountryDetailDao()
}