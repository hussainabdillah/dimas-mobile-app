package com.seisme.dimas.data.repository

import com.seisme.dimas.data.remote.api.ApiService
import com.seisme.dimas.data.remote.response.GempaItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GempaRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : GempaRepository {

    override fun getGempaData(): Flow<List<GempaItem>> = flow {
        try {
            val response = apiService.getDataGempa()
            if (response.isSuccessful) {
                response.body()?.let { gempaResponse ->
                    val formattedGempaItems = gempaResponse.infogempa.gempa.map { gempaItem ->
                        gempaItem.copy(
                            jam = gempaItem.formattedTime,
                            wilayah = gempaItem.formattedLocation
                        )
                    }
                    emit(formattedGempaItems)
                } ?: emit(emptyList())
            } else {
                emit(emptyList())
            }
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}