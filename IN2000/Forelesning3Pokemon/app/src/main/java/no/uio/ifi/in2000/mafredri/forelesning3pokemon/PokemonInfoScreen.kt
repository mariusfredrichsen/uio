package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment


@Composable
fun PokemonInfoScreen(pokemonInfoViewModel: PokemonInfoViewModel) {

    val pokemonInfo by pokemonInfoViewModel.pokemonInfoUIState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "pokemonInfo")
    }
}