package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import kotlinx.coroutines.runBlocking
import no.uio.ifi.in2000.mafredri.forelesning3pokemon.network.PokemonClient.client
import no.uio.ifi.in2000.mafredri.forelesning3pokemon.network.PokemonRoutes

class PokemonInfoRepository {

    suspend fun getAllInfo(): String {
        runBlocking {

        }
        return client.get(PokemonRoutes.POKEMON_INFO).body()
    }


}