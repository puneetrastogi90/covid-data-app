package com.covid.covidapp.repositories


import com.covid.covidapp.data.CountriesListModel
import com.covid.covidapp.data.CountryCovidDetailModel
import com.covid.covidapp.data.Result
import com.covid.covidapp.data.WorldReportModel
import com.covid.covidapp.datasources.CovidLocalDataSource
import com.covid.covidapp.datasources.CovidRemoteDataSource
import javax.inject.Inject

class CovidRepository @Inject constructor(
    private val covidRemoteDataSource: CovidRemoteDataSource,
    private val covidLocalDataSource: CovidLocalDataSource
) {

    suspend fun getWorldData(): Result<WorldReportModel, WorldReportModel> {
        val result = covidRemoteDataSource.getWorldCovidData()
        return result
    }

    suspend fun getCountriesList(): Result<CountriesListModel, CountriesListModel> {
        val result = covidRemoteDataSource.getCountriesList()
        return result
    }

    suspend fun getCountryCovidData(countryCode: String): Result<CountryCovidDetailModel, CountryCovidDetailModel> {
        val result = covidRemoteDataSource.getCountryCovidData(countryCode)
        return result
    }

    companion object {
        @Volatile
        private var INSTANCE: CovidRepository? = null

        fun getInstance(
            covidRemoteDataSource: CovidRemoteDataSource,
            covidLocalDataSource: CovidLocalDataSource
        ): CovidRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: CovidRepository(
                    covidRemoteDataSource, covidLocalDataSource
                ).also { INSTANCE = it }
            }
        }

    }
}