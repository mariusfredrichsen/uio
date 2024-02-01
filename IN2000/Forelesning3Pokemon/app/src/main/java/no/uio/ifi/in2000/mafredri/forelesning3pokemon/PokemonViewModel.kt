package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class PokemonUIState(
    val pokemons: List<Pokemon> = listOf(),
    val caughtPokemon: Set<Int>
)


class PokemonViewModel: ViewModel() {
    val pokemonRepository = PokemonRepository()

    val pokemonUIState: StateFlow<PokemonUIState> = combine(
        pokemonRepository.observePokemon(),
        pokemonRepository.observecatchedPokemons()
    ) {pokemon, caughtPokemon -> PokemonUIState(
        pokemons = pokemon,
        caughtPokemon = caughtPokemon
        )
    }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PokemonUIState(caughtPokemon = setOf())
        )

    fun catchPokemon(id: Int) {
        viewModelScope.launch {
            pokemonRepository.catchPokemon(id)
        }
    }

    fun freePokemon(id: Int) {
        viewModelScope.launch {
            pokemonRepository.freePokemon(id)
        }
    }


    init {
        viewModelScope.launch {
            pokemonRepository.loadPokemons()
        }
    }
}
