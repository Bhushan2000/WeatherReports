package com.bhushantechsolutions.whetherreports.data
import com.bhushantechsolutions.whetherreports.model.GeoResponse
import com.bhushantechsolutions.whetherreports.model.GeoResult
import com.bhushantechsolutions.whetherreports.model.WeatherResponse
import com.bhushantechsolutions.whetherreports.utils.ApiConstants
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
class WeatherApi(private val client: HttpClient) {
    suspend fun getCoordinates(city: String): GeoResult {
        val response: GeoResponse = client.get(
            ApiConstants.GEO_BASE_URL + ApiConstants.GEO_SEARCH
        ) {
            parameter("name", city.trim())
            parameter("count", ApiConstants.GEO_COUNT)
            parameter("language", ApiConstants.GEO_LANGUAGE)
            parameter("format", ApiConstants.GEO_FORMAT)
        }.body()

        if (response.results.isEmpty()) {
            throw Exception("City not found")
        }
        val result = response.results.first()
        if (result.latitude == null || result.longitude == null) {
            throw Exception("Invalid location data")
        }
        return result
    }
    suspend fun getWeather(lat: Double, lon: Double): WeatherResponse {
        return client.get(
            ApiConstants.WEATHER_BASE_URL + ApiConstants.WEATHER_FORECAST
        ) {
            parameter("latitude", lat)
            parameter("longitude", lon)
            parameter("current", ApiConstants.CURRENT_FIELDS.joinToString(","))
            parameter("hourly", ApiConstants.HOURLY_FIELDS.joinToString(","))
            parameter("daily", ApiConstants.DAILY_FIELDS.joinToString(","))
            parameter("temperature_unit", ApiConstants.TEMP_UNIT)
            parameter("windspeed_unit", ApiConstants.WIND_UNIT)
            parameter("timezone", ApiConstants.TIMEZONE)
        }.body()
    }
    companion object {
        fun create(): WeatherApi {
            val client = HttpClient {
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            print("Ktor===> $message")
                         }
                    }
                    level = LogLevel.ALL
                }
                install(ContentNegotiation) {
                    json(
                        Json {
                            ignoreUnknownKeys = true
                            prettyPrint = true
                            isLenient = true
                        }
                    )
                }
            }
            return WeatherApi(client)
        }
    }
}