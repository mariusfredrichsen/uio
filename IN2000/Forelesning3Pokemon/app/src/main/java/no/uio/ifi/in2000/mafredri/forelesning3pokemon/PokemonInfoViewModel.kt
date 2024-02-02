package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class PokemonInfoUIState(
    val pokemonInfo: String = ""
)

class PokemonInfoViewModel: ViewModel() {


    val pokemonInfoRepository = PokemonInfoRepository()

    val pokemonInfoUIState: StateFlow<List<String>> = pokemonInfoRepository.getInfo()

    init {
        viewModelScope.launch {
            try {
                pokemonInfoRepository.loadPokemon()
            } catch (e: Exception) {

            }
        }
    }
}