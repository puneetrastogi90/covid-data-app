package com.covid.covidapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.covid.covidapp.daos.CountriesListDao
import com.covid.covidapp.daos.CountryDetailDao
import com.covid.covidapp.daos.WorldDataDao
import com.covid.covidapp.data.CountriesListModelItem
import com.covid.covidapp.data.CountryCovidDetailModelItem
import com.covid.covidapp.data.WorldReportModelItem

@Database(
    entities = [WorldReportModelItem::class, CountriesListModelItem::class, CountryCovidDetailModelItem::class],
    version = 2
)
abstract class CovidRoomDatabase : RoomDatabase() {

    abstract fun getWorldDataDao(): WorldDataDao
    abstract fun getCountriesListDao(): CountriesListDao
    abstract fun getCountryDetailDao(): CountryDetailDao
}