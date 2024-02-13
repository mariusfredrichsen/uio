package no.uio.ifi.in2000.mafredri.oblig2.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson

object AlpacaClient {
    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            gson()
        }
    }
}