package com.covid.covidapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.covid.covidapp.data.CountriesListModel
import com.covid.covidapp.data.CountryCovidDetailModel
import com.covid.covidapp.data.WorldReportModel
import com.covid.covidapp.network.service.CovidRestService
import com.covid.covidapp.repositories.CovidRepository
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class ExampleUnitTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    var service: CovidRestService? = null

    @Mock
    var repository: CovidRepository? = null

    @Before
    fun setUp() {
        service = Mockito.mock(CovidRestService::class.java)
        repository = Mockito.mock(CovidRepository::class.java)
        MockitoAnnotations.initMocks(this)
    }

    @Test
    public fun fetchWorldDataApi_Success() {
        runBlocking {

            Mockito.`when`(service?.getWorldReportService()).thenReturn(
                Response.success(
                    WorldReportModel()
                )
            )
        }

    }

    @Test
    public fun fetchWorldDataApi_Null() {
        runBlocking {

            Mockito.`when`(service?.getWorldReportService()).thenReturn(
                null
            )
        }
    }

    @Test
    public fun fetchWorldDataApi_Error() {
        runBlocking {
            Mockito.`when`(service?.getWorldReportService()).thenReturn(
                Response.error(
                    400,
                    ResponseBody.create(MediaType.get("application/json"), "error")
                )
            )
        }
    }

    @Test
    public fun fetchCountriesListApi_Success() {
        runBlocking {

            Mockito.`when`(service?.getCountriesListService()).thenReturn(
                Response.success(
                    CountriesListModel()
                )
            )
        }

    }

    @Test
    public fun fetchCountriesListApi_Null() {
        runBlocking {

            Mockito.`when`(service?.getCountriesListService()).thenReturn(
                null
            )
        }
    }

    @Test
    public fun fetchCountriesListApi_Error() {
        runBlocking {
            Mockito.`when`(service?.getCountriesListService()).thenReturn(
                Response.error(
                    400,
                    ResponseBody.create(MediaType.get("application/json"), "error")
                )
            )
        }
    }

    @Test
    public fun fetchCountryDataApi_Success() {
        runBlocking {

            Mockito.`when`(service?.getCountryCovidDetailService("IN")).thenReturn(
                Response.success(
                    CountryCovidDetailModel()
                )
            )
        }

    }

    @Test
    public fun fetchCountryDataApi_Null() {
        runBlocking {

            Mockito.`when`(service?.getCountryCovidDetailService("IN")).thenReturn(
                null
            )
        }
    }

    @Test
    public fun fetchCountryDataApi_Error() {
        runBlocking {
            Mockito.`when`(service?.getCountryCovidDetailService("IN")).thenReturn(
                Response.error(
                    400,
                    ResponseBody.create(MediaType.get("application/json"), "error")
                )
            )
        }
    }
}