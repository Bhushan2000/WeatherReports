package com.bhushantechsolutions.whetherreports

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.bhushantechsolutions.whetherreports.data.LocationProvider
import com.bhushantechsolutions.whetherreports.ui.WeatherScreen

@Composable
@Preview
fun App(
    locationProvider: LocationProvider
) {
    MaterialTheme {
        WeatherScreen(locationProvider)
    }
}