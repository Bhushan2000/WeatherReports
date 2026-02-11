package com.bhushantechsolutions.whetherreports.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WeatherBackground() {
    val infiniteTransition = rememberInfiniteTransition(label = "bg")
    val cloudOffset by infiniteTransition.animateFloat(
        initialValue = -200f,
        targetValue = 1200f,
        animationSpec = infiniteRepeatable(
            animation = tween(25000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "cloud"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF87CEEB), // Sky blue
                        Color(0xFFE0F7FA)  // Light sky
                    )
                )
            )
    ) {
        Cloud(
            modifier = Modifier
                .offset(x = cloudOffset.dp, y = 80.dp)
                .size(180.dp)
                .alpha(0.6f)
        )
        Cloud(
            modifier = Modifier
                .offset(x = (cloudOffset - 400).dp, y = 150.dp)
                .size(220.dp)
                .alpha(0.4f)
        )
        Cloud(
            modifier = Modifier
                .offset(x = (cloudOffset - 800).dp, y = 40.dp)
                .size(150.dp)
                .alpha(0.5f)
        )
    }
}
