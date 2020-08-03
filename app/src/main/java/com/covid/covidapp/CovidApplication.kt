package com.covid.covidapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment

import com.covid.covidapp.di.AppComponent
import com.covid.covidapp.di.AppModule
import com.covid.covidapp.di.DaggerAppComponent

class CovidApplication : Application() {
    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        @JvmStatic
        fun appComponent(context: Context): AppComponent =
            (context as CovidApplication).appComponent
    }

    override fun onCreate() {
        super.onCreate()
    }
}

fun Fragment.appComponent() =
    CovidApplication.appComponent(requireActivity().applicationContext)