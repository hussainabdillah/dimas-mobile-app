package com.seisme.dimas.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.seisme.dimas.data.remote.api.ApiService
import com.seisme.dimas.data.repository.ShakeReportRepository
import dagger.Binds
import com.seisme.dimas.data.repository.EarthquakeRepository
import com.seisme.dimas.data.repository.EarthquakeRepositoryImpl
import com.seisme.dimas.ui.screens.mapScreen.LocationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Provide FirebaseAuth instance
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    // Provide Firestore
    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    // Provide a Context for app-wide usage
    @Provides
    @Singleton
    fun provideAppContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://data.bmkg.go.id/") // Ganti dengan base URL API BMKG yang sesuai
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provide
    @Provides
    @Singleton
    fun provideGempaRepository(apiService: ApiService): EarthquakeRepository {
        return EarthquakeRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideShakeReportRepository(firestore: FirebaseFirestore): ShakeReportRepository {
        return ShakeReportRepository(firestore)
    }

    @Singleton
    @Provides
    fun provideLocationProvider(@ApplicationContext context: Context): LocationProvider {
        return LocationProvider(context)
    }

}