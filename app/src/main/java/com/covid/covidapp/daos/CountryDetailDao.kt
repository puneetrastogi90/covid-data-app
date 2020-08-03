package com.covid.covidapp.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.covid.covidapp.data.CountriesListModelItem
import com.covid.covidapp.data.CountryCovidDetailModel
import com.covid.covidapp.data.CountryCovidDetailModelItem
import com.covid.covidapp.data.WorldReportModelItem

@Dao
interface CountryDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(countryDetail: CountryCovidDetailModelItem)


    @Query("SELECT * FROM countries_detail where code=:code")
    fun getCountryData(code: String): List<CountryCovidDetailModelItem>

}