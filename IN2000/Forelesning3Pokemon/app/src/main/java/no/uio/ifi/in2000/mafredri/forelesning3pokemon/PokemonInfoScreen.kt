package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import android.annotation.SuppressLint
import android.inputmethodservice.Keyboard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PokemonInfoScreen(pokemonViewModel: PokemonViewModel,  navController: NavController) {
    val pokemonUIState by pokemonViewModel.pokemonUIState.collectAsState()

    var pokemon by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            NavBar(navController = navController)
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            if (pokemonUIState.pokemonInfo.name.isNotBlank()) {
                Text(text = pokemonUIState.pokemonInfo.toString())
            } else {
                Text(text = pokemonUIState.pokemonInfo.name)
            }

            OutlinedTextField(
                value = pokemon,
                onValueChange = {pokemon = it},
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {pokemonViewModel.loadPokemonInfo(pokemon)})
            )

            Button(
                onClick = {
                        pokemonViewModel.loadPokemonInfo(pokemon)
                }
            ) {
                Text(text = "TRYKK PÅ MEG AIUSHFIUAHSF")
            }

        }

    }

}