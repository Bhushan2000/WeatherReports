package com.bhushantechsolutions.whetherreports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bhushantechsolutions.whetherreports.data.LocationProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val locationProvider = AndroidLocationProvider(this)
        setContent {
            App(locationProvider)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppAndroidPreview() {

    val fakeProvider = object : LocationProvider {
        override suspend fun getLocation(): Pair<Double, Double>? {
            return 21.1458 to 79.0882
        }
    }

    App(fakeProvider)
}
