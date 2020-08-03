package com.covid.covidapp.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.covid.covidapp.data.CountriesListModelItem
import com.covid.covidapp.data.WorldReportModelItem

@Dao
interface CountriesListDao {

    @Insert
    fun insert(countriesList: List<CountriesListModelItem>)

    @Query("DELETE FROM countries_list")
    fun nukeTable()

    @Query("SELECT * FROM countries_list")
    fun getAllCountriesFromDb(): List<CountriesListModelItem>

}