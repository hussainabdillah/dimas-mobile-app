package com.seisme.dimas.data.model

import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint

@IgnoreExtraProperties
data class ShakeReport(
    @JvmField val intensity: Int = 1,
    @JvmField val comment: String = "",
    @JvmField val floor: String = "",
    @JvmField val user: String = "",
    @JvmField val time: Timestamp = Timestamp.now(),
    @JvmField val location: GeoPoint = GeoPoint(0.0, 0.0)
)


