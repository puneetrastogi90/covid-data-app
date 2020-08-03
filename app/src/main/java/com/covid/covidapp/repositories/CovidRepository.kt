package com.covid.covidapp.repositories


import com.covid.covidapp.data.*
import com.covid.covidapp.datasources.CovidLocalDataSource
import com.covid.covidapp.datasources.CovidRemoteDataSource
import com.covid.covidapp.exceptions.NoInternetException
import javax.inject.Inject

class CovidRepository @Inject constructor(
    private val covidRemoteDataSource: CovidRemoteDataSource,
    private val covidLocalDataSource: CovidLocalDataSource
) {

    suspend fun getWorldData(): Result<WorldReportModel, WorldReportModel> {
        val result = covidRemoteDataSource.getWorldCovidData()
        if (result is Result.Success<*, *>) {
            covidLocalDataSource.removeAllWorldData()
            covidLocalDataSource.saveWorldreport((result.data as WorldReportModel).get(0))
            return result
        } else {
            if (result is Result.Error<*, *> && result.exception is NoInternetException) {
                val worldReportModel = WorldReportModel()
                worldReportModel.add(covidLocalDataSource.getCachedWorldReport())
                result.data = worldReportModel
            }
            return result
        }
        return result
    }

    suspend fun getCountriesList(): Result<CountriesListModel, CountriesListModel> {
        val result = covidRemoteDataSource.getCountriesList()
        if (result is Result.Success<*, *>) {
            covidLocalDataSource.removeAllCountriesListData()
            covidLocalDataSource.saveCountriesList(result.data as CountriesListModel)
        } else {
            if (result is Result.Error<*, *> && result.exception is NoInternetException) {
                val countriesListModel = CountriesListModel()
                countriesListModel.addAll(covidLocalDataSource.getAllCountriesFromDB())
                result.data = countriesListModel
            }
        }
        return result
    }

    suspend fun getCountryCovidData(countryCode: String): Result<CountryCovidDetailModel, CountryCovidDetailModel> {
        val result = covidRemoteDataSource.getCountryCovidData(countryCode)
        if (result is Result.Success<*, *>) {
            covidLocalDataSource.saveCountryData(result.data as CountryCovidDetailModel)
        } else {
            if (result is Result.Error<*, *> && result.exception is NoInternetException) {
                val countryCovidDetailModel = CountryCovidDetailModel()
                countryCovidDetailModel.addAll(covidLocalDataSource.getCountryData(countryCode))
                result.data = countryCovidDetailModel
            }
        }
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