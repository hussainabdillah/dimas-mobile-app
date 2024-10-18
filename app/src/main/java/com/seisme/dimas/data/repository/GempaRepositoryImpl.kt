package com.seisme.dimas.data.repository

import android.util.Log
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
                    emit(gempaResponse.infogempa.gempa) // Emit list gempa dari objek `Infogempa`
                    Log.d("GempaRepository", "Data berhasil diambil: ${gempaResponse.infogempa.gempa.size} item")
                } ?: emit(emptyList())
            } else {
                emit(emptyList())
                Log.e("GempaRepository", "Response error: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            emit(emptyList())
            Log.e("GempaRepository", "Exception saat mengambil data: ${e.message}", e)
        }
    }
}