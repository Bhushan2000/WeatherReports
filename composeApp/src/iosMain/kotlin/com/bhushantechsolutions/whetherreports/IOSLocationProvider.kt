package com.bhushantechsolutions.whetherreports

import com.bhushantechsolutions.whetherreports.data.LocationProvider

class IOSLocationProvider : LocationProvider {
    override suspend fun getLocation(): Pair<Double, Double>? {
        return null // implement later
    }
}
