package com.seisme.dimas.data.model

data class UserData(
    val email: String = "",
    val username: String = "",
    val contact: String = "",
    val gender: String = "",
    var documentId: String = "",
    val members: List<UserData> = emptyList()

)
