package com.bhushantechsolutions.whetherreports.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhushantechsolutions.whetherreports.data.WeatherApi
import com.bhushantechsolutions.whetherreports.data.WeatherRepository
import com.bhushantechsolutions.whetherreports.viewModel.WeatherViewModel
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.bhushantechsolutions.whetherreports.data.LocationProvider
import com.bhushantechsolutions.whetherreports.utils.UiState

@Composable
fun WeatherScreen(
    locationProvider: LocationProvider
) {

    val api = remember { WeatherApi.create() }
    val repo = remember { WeatherRepository(api) }

    val viewModel = remember {
        WeatherViewModel(repo, locationProvider)
    }

    var city by remember { mutableStateOf("") }

    val locationState = viewModel.locationWeatherState
    val cityState = viewModel.cityWeatherState

    // Decide which state to show
    val activeState = when {
        cityState is UiState.Loading -> cityState
        cityState is UiState.Success -> cityState
        cityState is UiState.Error -> cityState
        else -> locationState
    }

    // Load current location on first launch
    LaunchedEffect(Unit) {
        if (locationState is UiState.Idle) {
            viewModel.loadCurrentLocationWeather()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        WeatherBackground()

        Scaffold(
            containerColor = Color.Transparent
        ) { padding ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 24.dp)
                    .safeContentPadding(),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                /* ---------- Header ---------- */

                item {

                    Spacer(Modifier.height(16.dp))

                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn() + slideInVertically { -it / 2 }
                    ) {

                        Text(
                            text = "Weather App ☁️",
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(Modifier.height(24.dp))
                }


                /* ---------- Search Bar ---------- */

                item {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        OutlinedTextField(
                            value = city,
                            onValueChange = { city = it },
                            label = { Text("Enter City") },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            shape = RoundedCornerShape(16.dp)
                        )

                        Spacer(Modifier.width(8.dp))

                        IconButton(
                            enabled = activeState !is UiState.Loading,
                            onClick = {
                                viewModel.loadCurrentLocationWeather()
                                city = ""
                            },
                            modifier = Modifier
                                .size(56.dp)
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    CircleShape
                                )
                        ) {

                            Icon(
                                imageVector = Icons.Default.MyLocation,
                                contentDescription = "Use current location",
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(Modifier.height(16.dp))
                }


                /* ---------- Search Button ---------- */

                item {

                    Button(
                        onClick = {
                            if (city.isNotBlank()) {
                                viewModel.loadWeather(city)
                            }
                        },
                        enabled = activeState !is UiState.Loading,

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),

                        shape = RoundedCornerShape(16.dp)
                    ) {

                        AnimatedContent(
                            targetState = activeState is UiState.Loading,
                            label = "ButtonLoader"
                        ) { loading ->

                            if (loading) {

                                CircularProgressIndicator(
                                    modifier = Modifier.size(22.dp),
                                    strokeWidth = 2.dp
                                )

                            } else {

                                Text(
                                    "Get Weather",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(24.dp))
                }

                if (activeState is UiState.Error) {

                    item {
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn() + slideInVertically(),
                            exit = fadeOut()
                        ) {
                            Text(
                                text = activeState.message,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        Spacer(Modifier.height(16.dp))
                    }
                }
                if (activeState is UiState.Success) {
                    val data = activeState.data
                    item {
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn() + slideInVertically { it / 2 },
                            exit = fadeOut()
                        ) {
                            Column {
                                WeatherCard(
                                    data = data,
                                    cityName = city.ifBlank { "Current Location" }
                                )
                                Spacer(Modifier.height(24.dp))
                                HourlyForecast(data)
                                Spacer(Modifier.height(24.dp))
                                DailyForecast(data)
                                Spacer(Modifier.height(32.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewWeatherScreen() {
    val fakeProvider = object : LocationProvider {
        override suspend fun getLocation(): Pair<Double, Double>? {
            return 21.1458 to 79.0882
        }
    }
    WeatherScreen(fakeProvider)
}