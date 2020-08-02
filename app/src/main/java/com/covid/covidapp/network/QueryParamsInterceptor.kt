package com.covid.covidapp.network

import com.covid.covidapp.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class QueryParamsInterceptor @Inject constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val newReq = original.newBuilder().addHeader("x-rapidapi-key", BuildConfig.API_KEY).build()
        return chain.proceed(newReq)
    }
}