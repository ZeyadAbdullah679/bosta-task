package com.bosta.task.presentation

import androidx.lifecycle.ViewModel
import com.bosta.task.domain.models.City
import com.bosta.task.domain.repositories.CitiesRepository
import com.bosta.task.utils.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val citiesRepository: CitiesRepository
): ViewModel() {
    private val _cities = MutableStateFlow<DataState<List<City>>>(DataState.Empty)
    val cities: StateFlow<DataState<List<City>>> = _cities


    fun getCities(): DataState<List<City>> {
        return _cities.value
    }


}