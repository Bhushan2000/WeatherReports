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
import androidx.compose.material3.Divider
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
fun DailyForecast(data: WeatherResponse) {
    val days = data.daily?.time ?: return
    val min = data.daily.minTemp ?: return
    val max = data.daily.maxTemp ?: return
    val codes = data.daily.weatherCode ?: return
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "7 Day Forecast",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))
        days.take(7).forEachIndexed { index, date ->
            val ui = getWeatherUi(codes[index])
            DailyItem(
                day = date,
                min = min[index],
                max = max[index],
                icon = ui.icon
            )
            Divider()
        }
    }
}
