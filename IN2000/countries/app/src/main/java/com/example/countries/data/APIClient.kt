package com.example.countries.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson


object APIClient {
    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            gson()
        }
    }
}