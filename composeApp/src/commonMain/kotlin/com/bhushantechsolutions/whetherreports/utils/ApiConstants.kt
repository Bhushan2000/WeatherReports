package com.bhushantechsolutions.whetherreports.utils // change to your package

object ApiConstants {
    // Base URLs
    const val GEO_BASE_URL = "https://geocoding-api.open-meteo.com/v1/"
    const val WEATHER_BASE_URL = "https://api.open-meteo.com/v1/"
    // Endpoints
    const val GEO_SEARCH = "search"
    const val WEATHER_FORECAST = "forecast"
    // Geo API Defaults
    const val GEO_COUNT = 1
    const val GEO_LANGUAGE = "en"
    const val GEO_FORMAT = "json"
    // Weather Parameters
    const val TEMP_UNIT = "celsius"
    const val WIND_UNIT = "kmh"
    const val TIMEZONE = "auto"
    // Current Weather Fields
    val CURRENT_FIELDS = listOf(
        "temperature_2m",
        "relative_humidity_2m",
        "apparent_temperature",
        "weather_code",
        "wind_speed_10m"
    )
    // Hourly Forecast Fields
    val HOURLY_FIELDS = listOf(
        "temperature_2m",
        "relative_humidity_2m",
        "precipitation_probability",
        "weather_code"
    )
    // Daily Forecast Fields
    val DAILY_FIELDS = listOf(
        "temperature_2m_max",
        "temperature_2m_min",
        "sunrise",
        "sunset",
        "precipitation_sum",
        "weather_code"
    )
}
