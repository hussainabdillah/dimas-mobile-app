package com.seisme.dimas.data.repository

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.seisme.dimas.data.model.EarthquakeData
import com.seisme.dimas.data.model.ShakeReport
import com.seisme.dimas.data.remote.api.ApiService
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

// data dummy
//fun getUserLocation(): LatLng {
//    return LatLng(-6.355521,106.660981)
//}

//fun getLatestEarthquakeLocation(): EarthquakeData {
//    return EarthquakeData(LatLng(-7.081899,105.635777), 5)
//}

//fun getShakingReport(): List<ShakingReportData> {
//    return listOf(
//        ShakingReportData(LatLng(-6.701424, 105.688756), 3),
//        ShakingReportData(LatLng(-6.720401, 105.836792), 4),
//        ShakingReportData(LatLng(-6.734039, 106.105957), 5),
//        ShakingReportData(LatLng(-6.854040, 106.229553), 2),
//        ShakingReportData(LatLng(-6.924936, 106.333923), 1),
//        ShakingReportData(LatLng(-6.545796, 106.298218), 4)
//    )
//}

class MapRepository @Inject constructor(
    private val api: ApiService,
    private val firestore: FirebaseFirestore
) {
    suspend fun getLatestEarthquake(): EarthquakeData {
        val response = api.getLatestEarthquake().infogempa.gempa
        val coordinates = response.coordinates.split(",").map { it.trim().toDouble() }
        return EarthquakeData(
            date = response.tanggal,
            time = response.jam,
            coordinates = LatLng(coordinates[0], coordinates[1]),
            magnitude = response.magnitude.toDouble(),
            depth = response.kedalaman,
            region = response.wilayah,
            felt = response.dirasakan
        )
    }

    suspend fun getRecentShakeReports(): List<ShakeReport> {
        return try {
            firestore.collection("disaster_reports")
                .orderBy("time", Query.Direction.DESCENDING)
                .limit(20)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(ShakeReport::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }
}