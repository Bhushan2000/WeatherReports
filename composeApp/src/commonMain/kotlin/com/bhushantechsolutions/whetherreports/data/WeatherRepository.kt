package com.bhushantechsolutions.whetherreports.data

import com.bhushantechsolutions.whetherreports.model.WeatherResponse

class WeatherRepository(
    private val api: WeatherApi
) {
    suspend fun getWeather(city: String): WeatherResponse {
        val geo = api.getCoordinates(city)
        val lat = geo.latitude ?: throw Exception("Latitude missing")
        val lon = geo.longitude ?: throw Exception("Longitude missing")
        return api.getWeather(lat, lon)
    }

    suspend fun getWeatherByLatLon(lat: Double, lon: Double): WeatherResponse {
        return api.getWeather(lat, lon)
    }

}