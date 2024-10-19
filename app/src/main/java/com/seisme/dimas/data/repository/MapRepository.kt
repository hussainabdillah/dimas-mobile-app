package com.seisme.dimas.data.repository

import com.google.android.gms.maps.model.LatLng

fun getUserLocation(): LatLng {
    return LatLng(-6.355521,106.660981)
}

fun getLatestEarthquakeLocation(): LatLng {
    return LatLng(-7.081899,105.635777)
}

fun getShakingReport(): List<LatLng> {
    return listOf(
        LatLng(-6.701424,105.688756),
        LatLng(-6.720401,105.836792),
        LatLng(-6.734039,106.105957),
        LatLng(-6.854040,106.229553),
        LatLng(-6.924936,106.333923),
        LatLng(-6.545796,106.298218)
    )
}
