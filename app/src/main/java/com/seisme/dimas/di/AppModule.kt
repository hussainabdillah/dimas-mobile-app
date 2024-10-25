package com.seisme.dimas.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.seisme.dimas.data.remote.api.ApiService
import com.seisme.dimas.data.repository.EarthquakeRepository
import com.seisme.dimas.data.repository.EarthquakeRepositoryImpl
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
            .baseUrl("https://data.bmkg.go.id/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provide the EarthquakeRepository
    @Provides
    @Singleton
    fun provideEarthquakeRepository(apiService: ApiService): EarthquakeRepository {
        return EarthquakeRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}