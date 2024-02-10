package no.uio.ifi.in2000.mafredri.jokes.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*


object JokesClient {
    val client = HttpClient {
        install(ContentNegotiation) {
            gson()
        }
    }
}