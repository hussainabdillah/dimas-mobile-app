package com.seisme.dimas.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.seisme.dimas.data.model.ShakeReport
import javax.inject.Inject

class ShakeReportRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    fun submitShakeReport(report: ShakeReport, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        firestore.collection("disaster_reports")
            .add(report)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
    }
}
