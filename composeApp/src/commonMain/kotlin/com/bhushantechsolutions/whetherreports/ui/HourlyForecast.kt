package com.bhushantechsolutions.whetherreports.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bhushantechsolutions.whetherreports.model.WeatherResponse

@Composable
fun HourlyForecast(data: WeatherResponse) {
    val times = data.hourly?.time ?: return
    val temps = data.hourly.temperature ?: return
    val codes = data.hourly.weatherCode ?: return
    val rain = data.hourly.rainChance ?: return
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Next Hours",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(times.take(12).size) { index ->
                val hour = times[index].substringAfter("T").take(5)
                val ui = getWeatherUi(codes[index])
                HourlyItem(
                    hour = hour,
                    temp = temps[index],
                    icon = ui.icon,
                    rain = rain[index]
                )
            }
        }
    }
}
