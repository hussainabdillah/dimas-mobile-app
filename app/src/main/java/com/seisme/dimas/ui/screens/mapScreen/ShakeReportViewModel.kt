package com.seisme.dimas.ui.screens.mapScreen

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.GeoPoint
import com.seisme.dimas.data.model.ShakeReport
import com.seisme.dimas.data.repository.ShakeReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShakeReportViewModel @Inject constructor(
    private val repository: ShakeReportRepository
) : ViewModel() {

    var intensity by mutableIntStateOf(1)
    var comment by mutableStateOf("")
    var floor by mutableStateOf("")
    var latitude by mutableDoubleStateOf(0.0)
    var longitude by mutableDoubleStateOf(0.0)
    var isLoading by mutableStateOf(false)
    var isSuccess by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    fun submitReport(user: String) {
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


}