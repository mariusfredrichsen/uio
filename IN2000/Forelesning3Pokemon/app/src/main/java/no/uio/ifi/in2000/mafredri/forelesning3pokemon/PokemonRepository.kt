package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PokemonRepository {

    private val _caughtPokemons = MutableStateFlow<Set<Int>>(setOf())
    private val _pokemons = MutableStateFlow<MutableList<Pokemon>>(mutableListOf())

    fun observePokemon(): StateFlow<List<Pokemon>> = _pokemons.asStateFlow()
    fun observecatchedPokemons(): StateFlow<Set<Int>> = _caughtPokemons.asStateFlow()

    suspend fun catchPokemon(id: Int) {
        _caughtPokemons.update { earlierCaught ->
            earlierCaught.plus(id)
        }
    }

    suspend fun freePokemon(id: Int) {
        _caughtPokemons.update {earlierCaught ->
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
}