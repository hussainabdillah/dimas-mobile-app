package com.seisme.dimas.ui.screens.mapScreen


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.seisme.dimas.data.model.EarthquakeData
import com.seisme.dimas.data.model.ShakeReport
import com.seisme.dimas.data.repository.MapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: MapRepository,
    private val locationProvider: LocationProvider
) : ViewModel() {

    private val _userLocation = MutableStateFlow<LatLng?>(null)
    val userLocation: StateFlow<LatLng?> get() = _userLocation

    private val _earthquakeData = MutableLiveData<EarthquakeData?>()
    val earthquakeData: LiveData<EarthquakeData?> get() = _earthquakeData

    private val _shakingReports = mutableStateOf<List<ShakeReport>>(emptyList())
    val shakingReports: State<List<ShakeReport>> get() = _shakingReports

    init {
        fetchUserLocation()
    }

    fun fetchLatestEarthquake() {
        viewModelScope.launch {
            try {
                val data = repository.getLatestEarthquake()
                _earthquakeData.value = data
            } catch (e: Exception) {
                // Handle error bisa ditambahkan nanti bisa berupa ui atau log
            }
        }
    }

    fun fetchRecentShakeReports() {
        viewModelScope.launch {
            _shakingReports.value = repository.getRecentShakeReports()
        }
    }

    private fun fetchUserLocation() {
        viewModelScope.launch {
            _userLocation.value = locationProvider.getCurrentLocation()
        }
    }
}
