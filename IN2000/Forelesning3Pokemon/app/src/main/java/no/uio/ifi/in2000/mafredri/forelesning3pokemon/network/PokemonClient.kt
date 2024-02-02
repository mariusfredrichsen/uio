package no.uio.ifi.in2000.mafredri.forelesning3pokemon.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android

object PokemonClient {
    var client = HttpClient(Android) {

    }

}