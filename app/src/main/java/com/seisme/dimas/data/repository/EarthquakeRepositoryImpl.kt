package com.seisme.dimas.data.repository

import com.seisme.dimas.data.remote.api.ApiService
import com.seisme.dimas.data.remote.response.EarthquakeItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EarthquakeRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : EarthquakeRepository {

    override fun getEarthquakeData(): Flow<List<EarthquakeItem>> = flow {
        try {
            val response = apiService.getEarthquakeData()
            if (response.isSuccessful) {
                response.body()?.let { earthquakeResponse ->
                    val formattedEarthquakeItems = earthquakeResponse.earthquakeInfo.earthquakes.map { earthquakeItem ->
                        earthquakeItem.copy(
                            time = earthquakeItem.formattedTime,
                            location = earthquakeItem.formattedLocation
                        )
                    }
                    emit(formattedEarthquakeItems)
                } ?: emit(emptyList())
            } else {
                emit(emptyList())
            }
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}
