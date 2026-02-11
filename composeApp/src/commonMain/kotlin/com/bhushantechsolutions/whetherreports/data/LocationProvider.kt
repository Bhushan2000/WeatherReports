package com.bhushantechsolutions.whetherreports.data

interface LocationProvider {
    suspend fun getLocation(): Pair<Double, Double>?
}
