package com.seisme.dimas.ui.screens.mapScreen

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.tasks.await

class LocationProvider(context: Context) {

    private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): LatLng? {
        // Meminta lokasi terkini
        val location: Location? = fusedLocationClient.lastLocation.await()
        return location?.let {
            LatLng(it.latitude, it.longitude)
        }
    }
}
