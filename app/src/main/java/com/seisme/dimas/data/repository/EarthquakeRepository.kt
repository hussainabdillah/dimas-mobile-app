package com.seisme.dimas.data.repository

import com.seisme.dimas.data.remote.response.EarthquakeItem
import kotlinx.coroutines.flow.Flow

interface EarthquakeRepository {
    fun getEarthquakeData(): Flow<List<EarthquakeItem>>
}