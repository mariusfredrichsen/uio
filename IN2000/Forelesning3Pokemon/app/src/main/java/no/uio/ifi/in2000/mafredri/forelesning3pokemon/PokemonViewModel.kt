package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.mafredri.forelesning3pokemon.PokemonInfo

data class PokemonUIState(
    val pokemons: List<Pokemon> = listOf(),
    val caughtPokemon: Set<Int> = setOf(),
    val pokemonInfo: PokemonInfo = PokemonInfo(0, 0, 0, false, "0", "0", 0, 0)
)


class PokemonViewModel: ViewModel() {
    val pokemonRepository = PokemonRepository()

    val pokemonUIState: StateFlow<PokemonUIState> = combine(
        pokemonRepository.observePokemon(),
        pokemonRepository.observeCatchedPokemons(),
        pokemonRepository.getPokemonInfo()
    ) {pokemon, caughtPokemon, pokemonInfo -> PokemonUIState(
        pokemons = pokemon,
        caughtPokemon = caughtPokemon,
        pokemonInfo = pokemonInfo
        )
    }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PokemonUIState()
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

    fun loadPokemonInfo(name: String) {
        viewModelScope.launch {
            pokemonRepository.loadPokemonInfo(name)
        }
    }


    init {
        viewModelScope.launch {
            pokemonRepository.loadPokemons()
        }
    }
}
