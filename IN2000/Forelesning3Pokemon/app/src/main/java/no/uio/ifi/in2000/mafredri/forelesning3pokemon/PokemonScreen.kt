package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun PokemonScreen(pokemonViewModel: PokemonViewModel) {
    val pokemonUIState by pokemonViewModel.pokemonUIState.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(pokemonUIState.pokemons) { pokemon ->
            PokemonCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                pokemon = pokemon,
                isCatched = pokemonUIState.caughtPokemon.contains(pokemon.id),
                onCatch = { pokemonViewModel.catchPokemon(pokemon.id) }
            )
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
