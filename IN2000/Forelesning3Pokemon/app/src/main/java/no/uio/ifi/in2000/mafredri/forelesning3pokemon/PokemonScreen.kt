package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PokemonScreen(pokemonViewModel: PokemonViewModel, navController: NavController) {
    val pokemonUIState by pokemonViewModel.pokemonUIState.collectAsState()

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            NavBar(navController)
        }
    ) {
        LazyColumn() {
            items(pokemonUIState.pokemons) { pokemon ->
                PokemonCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    pokemon = pokemon,
                    isCatched = pokemonUIState.caughtPokemon.contains(pokemon.id),
                    onCatch = {
                        pokemonViewModel.catchPokemon(pokemon.id)

                        scope.launch {
                            val result = snackbarHostState
                                .showSnackbar(
                                    message = "Do you regret catching this " + pokemon.name + "?" ,
                                    actionLabel = "Free " + pokemon.name,
                                    duration = SnackbarDuration.Indefinite
                                )
                            when (result) {
                                SnackbarResult.ActionPerformed -> {
                                    pokemonViewModel.freePokemon(pokemon.id)
                                }

                                SnackbarResult.Dismissed -> {

                                }
                            }

                        }
                    }
                )
            }
        }
    }


}

@Composable
fun PokemonCard(modifier: Modifier = Modifier, pokemon: Pokemon, isCatched: Boolean, onCatch: () -> Unit) {
    Card(modifier = modifier) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier.size(96.dp),
                model = pokemon.pokemonImageUrl,
                contentDescription = null
            )
            Column {
                Text("Name: ${pokemon.name}")
                Text(text = "id: ${pokemon.id}")
            }
            Spacer(modifier = Modifier.weight(1f))
            if (isCatched) {
                Text(text = "Jeg er fanget!")
            } else {
                Button(onClick = { onCatch() }) {
                    Text(text = "Fang meg!")
                }
            }
        }
    }
}

@Preview
@Composable
fun PokemonCardPreview() {
    val pokemon = Pokemon(
        id = 142,
        name = "Snorlax",
        pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/143.png"

    )
    PokemonCard(pokemon = pokemon, onCatch = {}, isCatched = true)
}
