package com.bhushantechsolutions.whetherreports.viewModel
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhushantechsolutions.whetherreports.data.LocationProvider
import com.bhushantechsolutions.whetherreports.data.WeatherRepository
import com.bhushantechsolutions.whetherreports.model.WeatherResponse
import com.bhushantechsolutions.whetherreports.utils.UiState
import kotlinx.coroutines.*
class WeatherViewModel(
    private val repository: WeatherRepository,
    private val locationProvider: LocationProvider
) : ViewModel() {
    var cityWeatherState by mutableStateOf<UiState<WeatherResponse>>(UiState.Idle)
        private set
    var locationWeatherState by mutableStateOf<UiState<WeatherResponse>>(UiState.Idle)
        private set

    fun loadWeather(city: String) {
        if (city.isBlank()) {
            cityWeatherState = UiState.Error("Enter city name")
            return
        }
        viewModelScope.launch {
            cityWeatherState = UiState.Loading
            try {
                val data = repository.getWeather(city)
                cityWeatherState = UiState.Success(data)
            } catch (e: Exception) {
                cityWeatherState = UiState.Error(
                    e.message ?: "Something went wrong",
                    e
                )
            }
        }
    }

    fun loadCurrentLocationWeather() {
        viewModelScope.launch {
            locationWeatherState = UiState.Loading
            try {
                val location = locationProvider.getLocation()
                if (location != null) {
                    val (lat, lon) = location
                    val data =
                        repository.getWeatherByLatLon(lat, lon)
                    locationWeatherState = UiState.Success(data)
                } else {
                    locationWeatherState =
                        UiState.Error("Location not available")
                }
            } catch (e: Exception) {
                locationWeatherState = UiState.Error(
                    e.message ?: "Failed to get location",
                    e
                )
            }
        }
    }
}


