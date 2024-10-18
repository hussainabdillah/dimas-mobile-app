package com.seisme.dimas.data.remote.api

import com.seisme.dimas.data.remote.response.GempaItem
import com.seisme.dimas.data.remote.response.GempaResponse
import retrofit2.http.GET
import retrofit2.Response

interface ApiService {
    @GET("DataMKG/TEWS/gempadirasakan.json")
    suspend fun getDataGempa(): Response<GempaResponse>
}
