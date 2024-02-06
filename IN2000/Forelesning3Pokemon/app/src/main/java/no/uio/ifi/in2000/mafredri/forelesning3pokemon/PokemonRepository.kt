package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import io.ktor.client.call.body
import io.ktor.client.plugins.onDownload
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import no.uio.ifi.in2000.mafredri.forelesning3pokemon.network.PokemonClient

class PokemonRepository {

    private val _pokemons = MutableStateFlow<MutableList<Pokemon>>(mutableListOf())
    private val _caughtPokemons = MutableStateFlow<Set<Int>>(setOf())
    private val _pokemonInfo =
        MutableStateFlow<PokemonInfo>(PokemonInfo(0, 0, 0, false, "0", "0", 0, 0))

    fun observePokemon(): StateFlow<List<Pokemon>> = _pokemons.asStateFlow()

    fun observeCatchedPokemons(): StateFlow<Set<Int>> = _caughtPokemons.asStateFlow()

    fun getPokemonInfo(): StateFlow<PokemonInfo> = _pokemonInfo.asStateFlow()

    suspend fun catchPokemon(id: Int) {
        _caughtPokemons.update { earlierCaught ->
            earlierCaught.plus(id)
        }
    }

    suspend fun freePokemon(id: Int) {
        _caughtPokemons.update { earlierCaught ->
            earlierCaught.minus(id)
        }
    }

    suspend fun loadPokemons() {
        val pokemonFromWeb = mutableListOf(
            Pokemon(
                id = 1,
                name = "Bulbasaur",
                pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
            ),
            Pokemon(
                id = 4,
                name = "Charmander",
                pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"
            ),
            Pokemon(
                id = 7,
                name = "Squirtle",
                pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png"
            ),
            Pokemon(
                id = 387,
                name = "Turtwig",
                pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/387.png"
            ),
            Pokemon(
                id = 498,
                name = "Tepig",
                pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/498.png"
            ),
            Pokemon(
                id = 143,
                name = "Snorlax",
                pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/143.png"
            ),
            Pokemon(
                id = 105,
                name = "Marowak",
                pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/105.png",
            ),
            Pokemon(
                id = 72,
                name = "Tentacool",
                pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/72.png",
            ),
            Pokemon(
                id = 254,
                name = "Sceptile",
                pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/254.png",
            ),
            Pokemon(
                id = 25,
                name = "Pikachu",
                pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/25.png",
            )
        )

        _pokemons.update {
            pokemonFromWeb
        }
    }

    suspend fun loadPokemonInfo(pokemon: String) {
        _pokemonInfo.update {
            runBlocking {
                val httpResponse: HttpResponse =
                    PokemonClient.client.get("https://pokeapi.co/api/v2/pokemon/$pokemon") {
                        onDownload { bytesSentTotal, contentLength ->
                            println("Received $bytesSentTotal bytes from $contentLength")
                        }
                    }
                val pokemonInfo: PokemonInfo = httpResponse.body()
                pokemonInfo
            }
        }
    }
}