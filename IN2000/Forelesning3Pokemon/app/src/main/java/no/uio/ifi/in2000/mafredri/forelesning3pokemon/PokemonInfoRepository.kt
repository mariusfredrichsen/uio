package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PokemonInfoRepository {
    private val _pokemonInfo = MutableStateFlow<List<String>>(listOf())



    fun getInfo(): StateFlow<List<String>> = _pokemonInfo.asStateFlow()

    suspend fun loadPokemon() {
        _pokemonInfo.update {
            it
        }
    }
}