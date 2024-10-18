package com.seisme.dimas.data.repository

import com.seisme.dimas.data.remote.response.GempaItem
import kotlinx.coroutines.flow.Flow

interface GempaRepository {
    fun getGempaData(): Flow<List<GempaItem>>
}