package com.seisme.dimas.ui.screens.mapScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seisme.dimas.data.model.EarthquakeData
import com.seisme.dimas.data.repository.MapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: MapRepository
) : ViewModel() {

    private val _earthquakeData = MutableLiveData<EarthquakeData?>()
    val earthquakeData: LiveData<EarthquakeData?> get() = _earthquakeData

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
}
