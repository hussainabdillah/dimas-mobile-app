package com.seisme.dimas.data.remote.api

import com.seisme.dimas.data.remote.response.EarthquakeResponse
import com.seisme.dimas.data.remote.response.LatestEarthquakeResponse
import retrofit2.http.GET
import retrofit2.Response

interface ApiService {
    @GET("DataMKG/TEWS/gempadirasakan.json")
    suspend fun getEarthquakeData(): Response<EarthquakeResponse>

    @GET("DataMKG/TEWS/autogempa.json") // Ganti dengan endpoint API BMKG yang sesuai
    suspend fun getLatestEarthquake(): LatestEarthquakeResponse
}
