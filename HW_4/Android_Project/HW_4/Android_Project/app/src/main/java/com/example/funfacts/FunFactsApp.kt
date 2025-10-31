package com.example.funfacts

import android.app.Application
import androidx.room.Room
import com.example.funfacts.data.FunFactsDatabase
import com.example.funfacts.data.FunFactsRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class FunFactsApp : Application() {
    // Singletons (lazy)
    val httpClient: HttpClient by lazy {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
        }
    }

    val database: FunFactsDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            FunFactsDatabase::class.java,
            "funfacts.db"
        ).fallbackToDestructiveMigration().build()
    }

    val repository: FunFactsRepository by lazy {
        FunFactsRepository.getInstance(database.funFactDao(), httpClient)
    }
}
