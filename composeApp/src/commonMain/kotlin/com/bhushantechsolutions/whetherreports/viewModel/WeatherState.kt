package com.bhushantechsolutions.whetherreports.viewModel
import com.bhushantechsolutions.whetherreports.model.WeatherResponse
data class WeatherState(
    val cityName: String = "",
    val isLoading: Boolean = false,
    val weather: WeatherResponse? = null,
    val error: String? = null
)