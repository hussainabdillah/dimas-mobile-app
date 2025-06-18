package com.seisme.dimas.ui.screens.timelineScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seisme.dimas.data.remote.response.EarthquakeItem
import com.seisme.dimas.data.repository.EarthquakeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimelineViewModel @Inject constructor(
    private val earthquakeRepository: EarthquakeRepository
) : ViewModel() {

    private val _earthquakeData = MutableStateFlow<List<EarthquakeItem>>(emptyList())
    val earthquakeData: StateFlow<List<EarthquakeItem>> = _earthquakeData

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchEarthquakeData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                earthquakeRepository.getEarthquakeData().collect { earthquakeList ->
                    _earthquakeData.value = earthquakeList
                    _errorMessage.value = null
                }
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load data: ${e.message}"
                _earthquakeData.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
