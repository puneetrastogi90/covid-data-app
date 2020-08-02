package com.covid.covidapp.viewmodels

import androidx.lifecycle.ViewModel
import com.covid.covidapp.repositories.CovidRepository
import javax.inject.Inject

class CovidViewModel @Inject constructor(private val repository: CovidRepository) : ViewModel() {

}