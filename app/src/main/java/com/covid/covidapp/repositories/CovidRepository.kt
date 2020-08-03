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
            covidLocalDataSource.removeAll()
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