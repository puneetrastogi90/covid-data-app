package com.covid.covidapp.datasources


import com.covid.covidapp.data.CountriesListModel
import com.covid.covidapp.data.CountryCovidDetailModel
import com.covid.covidapp.data.Result
import com.covid.covidapp.data.WorldReportModel
import com.covid.covidapp.exceptions.NoInternetException
import com.covid.covidapp.network.service.CovidRestService
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class CovidRemoteDataSource @Inject constructor(val service: CovidRestService) {

    suspend fun getWorldCovidData(): Result<WorldReportModel, WorldReportModel> {
        var response: Response<WorldReportModel>? = null
        try {
            response = service.getWorldReportService()
            if (response?.isSuccessful) {
                val data = response.body()!!
                return Result.Success(data)
            }
        } catch (nointernetExcpetion: NoInternetException) {
            return Result.Error(nointernetExcpetion)
        }

        return Result.Error(IOException("Error while Fetching World Covid Data"))
    }

    suspend fun getCountriesList(): Result<CountriesListModel, CountriesListModel> {
        var response: Response<CountriesListModel>? = null
        try {
            response = service.getCountriesListService()
            if (response?.isSuccessful) {
                val data = response.body()!!
                return Result.Success(data)
            }
        } catch (nointernetExcpetion: NoInternetException) {
            return Result.Error(nointernetExcpetion)
        }

        return Result.Error(IOException("Error while Fetching Countries List."))
    }

    suspend fun getCountryCovidData(countryCode: String): Result<CountryCovidDetailModel, CountryCovidDetailModel> {
        var response: Response<CountryCovidDetailModel>? = null
        try {
            response = service.getCountryCovidDetailService(countryCode)
            if (response?.isSuccessful) {
                val data = response.body()!!
                return Result.Success(data)
            }
        } catch (nointernetExcpetion: NoInternetException) {
            return Result.Error(nointernetExcpetion)
        }

        return Result.Error(IOException("Error while Fetching Covid Detail of Country with Code: ${countryCode}"))
    }
}