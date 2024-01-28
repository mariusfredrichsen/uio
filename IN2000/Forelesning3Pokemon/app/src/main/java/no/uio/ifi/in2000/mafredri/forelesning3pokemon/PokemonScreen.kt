package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

fun getPokemons() = listOf(
    Pokemon(
        id = 1,
        name = "Bulbasaur",
        pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
    ),
    Pokemon(
        id = 4,
        name = "Charmander",
        pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"
    ),
    Pokemon(
        id = 7,
        name = "Squirtle",
        pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png"
    ),
    Pokemon(
        id = 387,
        name = "Turtwig",
        pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/387.png"
    ),
    Pokemon(
        id = 498,
        name = "Tepig",
        pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/498.png"
    ),
    Pokemon(
        id = 143,
        name = "Snorlax",
        pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/143.png"
    ),
    Pokemon(
        id = 105,
        name = "Marowak",
        pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/105.png",
    ),
    Pokemon(
        id = 72,
        name = "Tentacool",
        pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/72.png",
    ),
    Pokemon(
        id = 254,
        name = "Sceptile",
        pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/254.png",
    ),
    Pokemon(
        id = 25,
        name = "Pikachu",
        pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/25.png",
    )
)

@Composable
fun PokemonScreen() {
    val pokemons = remember {
        getPokemons().sortedBy { it.id }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(pokemons) { pokemon ->
            PokemonCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                pokemon = pokemon
            )
        }
    }
}

@Composable
fun PokemonCard(modifier: Modifier = Modifier, pokemon: Pokemon) {
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
    PokemonCard(pokemon = pokemon)
}
