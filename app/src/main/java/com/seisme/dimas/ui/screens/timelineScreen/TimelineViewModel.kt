package com.seisme.dimas.ui.screens.timelineScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seisme.dimas.data.remote.api.ApiConfig
import com.seisme.dimas.data.remote.response.GempaItem
import com.seisme.dimas.data.repository.GempaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimelineViewModel @Inject constructor(
    private val gempaRepository: GempaRepository
) : ViewModel() {

    private val _gempaData = MutableStateFlow<List<GempaItem>>(emptyList())
    val gempaData: StateFlow<List<GempaItem>> = _gempaData

    fun getGempaData() {
        viewModelScope.launch {
            gempaRepository.getGempaData().collect { gempaList ->
                _gempaData.value = gempaList
            }
        }
    }
}
