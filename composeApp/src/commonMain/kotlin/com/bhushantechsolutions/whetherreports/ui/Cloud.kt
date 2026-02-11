package com.bhushantechsolutions.whetherreports.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
@Composable
fun Cloud(
    modifier: Modifier = Modifier,
    color: Color = Color.White
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // Main body
        Box(
            modifier = Modifier
                .size(width = 120.dp, height = 60.dp)
                .clip(RoundedCornerShape(50))
                .background(color)
        )
        // Left puff
        Box(
            modifier = Modifier
                .size(50.dp)
                .offset(x = (-40).dp, y = (-15).dp)
                .clip(CircleShape)
                .background(color)
        )
        // Center puff
        Box(
            modifier = Modifier
                .size(70.dp)
                .offset(y = (-25).dp)
                .clip(CircleShape)
                .background(color)
        )
        // Right puff
        Box(
            modifier = Modifier
                .size(55.dp)
                .offset(x = 40.dp, y = (-10).dp)
                .clip(CircleShape)
                .background(color)
        )
    }
}
