package no.uio.ifi.in2000.mafredri_oblig2.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson

object APIClient {
    val client: HttpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            gson()
        }
    }
}