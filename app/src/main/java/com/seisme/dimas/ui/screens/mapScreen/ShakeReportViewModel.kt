package com.seisme.dimas.ui.screens.mapScreen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.seisme.dimas.data.model.ShakeReport
import com.seisme.dimas.data.model.UserData
import com.seisme.dimas.data.repository.ShakeReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShakeReportViewModel @Inject constructor(
    private val repository: ShakeReportRepository,
    private val context: Context
) : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val _userData = MutableLiveData<UserData?>()
    val userData: LiveData<UserData?> = _userData

    init {
        val uid = auth.currentUser?.uid
        if (uid != null) {
            firestore.collection("users").document(uid).get()
                .addOnSuccessListener { document ->
                    _userData.value = document.toObject(UserData::class.java)
                }
                .addOnFailureListener {
                    // Handle error
                }
        }
    }

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    var intensity by mutableIntStateOf(1)
    var comment by mutableStateOf("")
    var floor by mutableStateOf("")
    var latitude by mutableDoubleStateOf(0.0)
    var longitude by mutableDoubleStateOf(0.0)
    var isLoading by mutableStateOf(false)
    var isSuccess by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    @SuppressLint("MissingPermission") // Suppress permission check warning if permissions are handled
    fun getLocationAndSubmitReport(user: String) {
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            errorMessage = "Location permissions not granted"
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                latitude = location.latitude
                longitude = location.longitude
                updateUserLocation()
                submitReport(user)
            } else {
                isLoading = false
                errorMessage = "Failed to retrieve location"
            }
        }.addOnFailureListener {
            isLoading = false
            errorMessage = it.localizedMessage ?: "Failed to retrieve location"
        }
    }

    private fun submitReport(user: String) {
        isLoading = true
        val report = ShakeReport(
            intensity = intensity,
            comment = comment,
            floor = floor,
            user = user,
            location = GeoPoint(latitude, longitude)
        )
        repository.submitShakeReport(report,
            onSuccess = {
                isLoading = false
                isSuccess = true
            },
            onError = { exception ->
                isLoading = false
                errorMessage = exception.localizedMessage ?: "Unknown Error"
            }
        )
    }

    private fun updateUserLocation() {
        val currentUserId = auth.currentUser?.uid ?: return
        val userRef = firestore.collection("users").document(currentUserId)

        val locationData = GeoPoint(latitude, longitude)
        userRef.update("location", locationData)
            .addOnSuccessListener {
                // Lokasi berhasil diperbarui
            }
            .addOnFailureListener { exception ->
                errorMessage = exception.localizedMessage ?: "Failed to update location"
            }
    }
}