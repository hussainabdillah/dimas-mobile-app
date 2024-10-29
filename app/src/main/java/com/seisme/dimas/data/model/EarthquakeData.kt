package com.seisme.dimas.data.model

import com.google.android.gms.maps.model.LatLng

data class EarthquakeData(
    val date: String,
    val time: String,
    val coordinates: LatLng,
    val magnitude: Double,
    val depth: String,
    val region: String,
    val felt: String
)