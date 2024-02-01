package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import okio.IOException

class PokemonInfoRepository {
    private val _pokemonInfo = MutableStateFlow<String>("")

    fun getInfo(): StateFlow<String> = _pokemonInfo.asStateFlow()

    suspend fun loadPokemon() {
        _pokemonInfo.update {
            PokemonApi.pokedex.getPokemonInfo()
        }
    }
}