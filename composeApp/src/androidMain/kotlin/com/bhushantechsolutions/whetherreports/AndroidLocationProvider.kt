package com.bhushantechsolutions.whetherreports

import android.annotation.SuppressLint
import android.content.Context
import com.bhushantechsolutions.whetherreports.data.LocationProvider
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine

class AndroidLocationProvider(
    private val context: Context
) : LocationProvider {
    private val client = LocationServices.getFusedLocationProviderClient(context)
    @SuppressLint("MissingPermission")
    override suspend fun getLocation(): Pair<Double, Double>? {
        return suspendCancellableCoroutine { cont ->
            client.lastLocation
                .addOnSuccessListener {
                    if (it != null) {
                        cont.resume(it.latitude to it.longitude, null)
                    } else {
                        cont.resume(null, null)
                    }
                }
                .addOnFailureListener {
                    cont.resume(null, null)
                }
        }
    }
}
