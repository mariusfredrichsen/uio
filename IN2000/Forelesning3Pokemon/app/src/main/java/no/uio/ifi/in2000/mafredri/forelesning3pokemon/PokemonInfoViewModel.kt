package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.IOException

data class PokemonInfoUIState(
    val pokemonInfo: String = ""
)

class PokemonInfoViewModel: ViewModel() {


    val pokemonInfoRepository = PokemonInfoRepository()

    val pokemonInfoUIState: StateFlow<String> = pokemonInfoRepository.getInfo()

    init {
        viewModelScope.launch {
            try {
                PokemonApi.pokedex.getPokemonInfo()
            } catch (e: Exception) {

            }
        }
    }
}