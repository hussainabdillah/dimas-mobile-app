package com.seisme.dimas.data.model

import com.google.firebase.firestore.GeoPoint

data class UserData(
    val email: String = "",
    val username: String = "",
    val contact: String = "",
    val gender: String = "",
    var documentId: String = "",
    var location: GeoPoint = GeoPoint(0.0, 0.0),
    val members: List<UserData> = emptyList()

)
