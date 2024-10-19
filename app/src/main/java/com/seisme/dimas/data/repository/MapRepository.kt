package com.seisme.dimas.data.repository

import com.google.android.gms.maps.model.LatLng

data class EarthquakeData(
    val location: LatLng,
    val magnitude: Int
)

data class ShakingReportData(
    val location: LatLng,
    val shakePower: Int
)

fun getUserLocation(): LatLng {
    return LatLng(-6.355521,106.660981)
}

fun getLatestEarthquakeLocation(): EarthquakeData {
    return EarthquakeData(LatLng(-7.081899,105.635777), 5)
}

fun getShakingReport(): List<ShakingReportData> {
    return listOf(
        ShakingReportData(LatLng(-6.701424, 105.688756), 3),
        ShakingReportData(LatLng(-6.720401, 105.836792), 4),
        ShakingReportData(LatLng(-6.734039, 106.105957), 5),
        ShakingReportData(LatLng(-6.854040, 106.229553), 2),
        ShakingReportData(LatLng(-6.924936, 106.333923), 1),
        ShakingReportData(LatLng(-6.545796, 106.298218), 4)
    )
}