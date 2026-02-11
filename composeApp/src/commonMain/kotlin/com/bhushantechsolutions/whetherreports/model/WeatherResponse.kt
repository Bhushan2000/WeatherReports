package com.bhushantechsolutions.whetherreports.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class WeatherResponse(
    val latitude: Double? = null,
    val longitude: Double? = null,
    @SerialName("generationtime_ms")
    val generationTimeMs: Double? = null,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int? = null,
    val timezone: String? = null,
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String? = null,
    val elevation: Double? = null,
    @SerialName("current_units")
    val currentUnits: CurrentUnits? = null,
    val current: CurrentWeather? = null,
    @SerialName("hourly_units")
    val hourlyUnits: HourlyUnits? = null,
    val hourly: HourlyWeather? = null,
    @SerialName("daily_units")
    val dailyUnits: DailyUnits? = null,
    val daily: DailyWeather? = null
)
@Serializable
data class CurrentUnits(

    val time: String? = null,

    @SerialName("temperature_2m")
    val temperature: String? = null,

    @SerialName("relative_humidity_2m")
    val humidity: String? = null,

    @SerialName("apparent_temperature")
    val apparentTemperature: String? = null,

    @SerialName("weather_code")
    val weatherCode: String? = null,

    @SerialName("wind_speed_10m")
    val windSpeed: String? = null
)
@Serializable
data class CurrentWeather(

    val time: String? = null,
    val interval: Int? = null,

    @SerialName("temperature_2m")
    val temperature: Double? = null,

    @SerialName("relative_humidity_2m")
    val humidity: Int? = null,

    @SerialName("apparent_temperature")
    val feelsLike: Double? = null,

    @SerialName("weather_code")
    val weatherCode: Int? = null,

    @SerialName("wind_speed_10m")
    val windSpeed: Double? = null
)
@Serializable
data class HourlyUnits(

    val time: String? = null,

    @SerialName("temperature_2m")
    val temperature: String? = null,

    @SerialName("relative_humidity_2m")
    val humidity: String? = null,

    @SerialName("precipitation_probability")
    val rainChance: String? = null,

    @SerialName("weather_code")
    val weatherCode: String? = null
)
@Serializable
data class HourlyWeather(

    val time: List<String>? = null,

    @SerialName("temperature_2m")
    val temperature: List<Double>? = null,

    @SerialName("relative_humidity_2m")
    val humidity: List<Int>? = null,

    @SerialName("precipitation_probability")
    val rainChance: List<Int>? = null,

    @SerialName("weather_code")
    val weatherCode: List<Int>? = null
)
@Serializable
data class DailyUnits(

    val time: String? = null,

    @SerialName("temperature_2m_max")
    val maxTemp: String? = null,

    @SerialName("temperature_2m_min")
    val minTemp: String? = null,

    val sunrise: String? = null,
    val sunset: String? = null,

    @SerialName("precipitation_sum")
    val rainSum: String? = null,

    @SerialName("weather_code")
    val weatherCode: String? = null
)
@Serializable
data class DailyWeather(

    val time: List<String>? = null,

    @SerialName("temperature_2m_max")
    val maxTemp: List<Double>? = null,

    @SerialName("temperature_2m_min")
    val minTemp: List<Double>? = null,

    val sunrise: List<String>? = null,
    val sunset: List<String>? = null,

    @SerialName("precipitation_sum")
    val rainSum: List<Double>? = null,

    @SerialName("weather_code")
    val weatherCode: List<Int>? = null
)
