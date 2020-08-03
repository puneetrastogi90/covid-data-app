package com.covid.covidapp.datasources


import com.covid.covidapp.daos.CountriesListDao
import com.covid.covidapp.daos.CountryDetailDao
import com.covid.covidapp.daos.WorldDataDao
import com.covid.covidapp.data.CountriesListModelItem
import com.covid.covidapp.data.CountryCovidDetailModel
import com.covid.covidapp.data.CountryCovidDetailModelItem
import com.covid.covidapp.data.WorldReportModelItem
import javax.inject.Inject

class CovidLocalDataSource @Inject constructor(
    val worldDataDao: WorldDataDao,
    val countriesListDao: CountriesListDao,
    val countryDetailDao: CountryDetailDao
) {

    fun removeAllWorldData() {
        worldDataDao.nukeTable()
    }

    fun removeAllCountriesListData() {
        countriesListDao.nukeTable()
    }

    fun saveWorldreport(worldReportModelItem: WorldReportModelItem) {
        worldDataDao.insert(worldReportModelItem)
    }

    fun getCachedWorldReport(): WorldReportModelItem {
        return worldDataDao.getAllWorldReportFromDb().get(0)
    }

    fun saveCountriesList(countryListModel: List<CountriesListModelItem>) {
        countriesListDao.insert(countryListModel)
    }

    fun getAllCountriesFromDB(): List<CountriesListModelItem> {
        return countriesListDao.getAllCountriesFromDb()
    }

    fun saveCountryData(countryCovidDetailModel: CountryCovidDetailModel) {
        countryDetailDao.insert(countryCovidDetailModel.get(0))
    }

    fun getCountryData(countryCode: String): List<CountryCovidDetailModelItem> {
        return countryDetailDao.getCountryData(countryCode)
    }


}