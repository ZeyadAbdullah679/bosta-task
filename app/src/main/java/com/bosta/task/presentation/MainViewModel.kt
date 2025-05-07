package com.bosta.task.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosta.task.data.mappers.toCity
import com.bosta.task.domain.models.City
import com.bosta.task.domain.usecases.GetAllCitiesUseCase
import com.bosta.task.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllCitiesUseCase: GetAllCitiesUseCase
) : ViewModel() {

    private val _cities = MutableStateFlow<DataState<List<City>>>(DataState.Loading)
    val cities: StateFlow<DataState<List<City>>> = _cities

    private var allCities: List<City> = emptyList()

    fun fetchCities() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllCitiesUseCase().collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> _cities.value = DataState.Loading

                    is DataState.Success -> {
                        allCities = dataState.data.map { it.toCity() }
                        _cities.value = DataState.Success(allCities)
                    }

                    is DataState.Error -> _cities.value = DataState.Error(dataState.exception)
                }
            }
        }
    }

    fun searchCities(query: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val filtered = if (query.isBlank()) {
                allCities
            } else {
                allCities.filter { city ->
                    city.name.contains(query, ignoreCase = true) ||
                            city.districts.any { it.districtName.contains(query, ignoreCase = true) }
                }
            }
            _cities.value = DataState.Success(filtered)
        }
    }
}
