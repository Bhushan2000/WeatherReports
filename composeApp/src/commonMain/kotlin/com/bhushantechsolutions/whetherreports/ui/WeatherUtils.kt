package com.bhushantechsolutions.whetherreports.ui


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Grain
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Thunderstorm
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector

data class WeatherUiModel(
    val icon: ImageVector,
    val description: String
)

fun getWeatherUi(code: Int?): WeatherUiModel {
    return when (code) {
        0 -> WeatherUiModel(Icons.Default.WbSunny, "Clear Sky")
        in 1..3 -> WeatherUiModel(Icons.Default.Cloud, "Partly Cloudy")
        in 45..48 -> WeatherUiModel(Icons.Default.Grain, "Fog")
        in 51..67 -> WeatherUiModel(Icons.Default.WaterDrop, "Rain")
        in 71..77 -> WeatherUiModel(Icons.Default.AcUnit, "Snow")
        in 80..82 -> WeatherUiModel(Icons.Default.Thunderstorm, "Showers")
        in 95..99 -> WeatherUiModel(Icons.Default.Thunderstorm, "Storm")
        else -> WeatherUiModel(Icons.Default.Help, "Unknown")
    }
}
