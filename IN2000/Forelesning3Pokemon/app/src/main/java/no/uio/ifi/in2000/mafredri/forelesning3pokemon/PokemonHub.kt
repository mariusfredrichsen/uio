package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun PokemonHub(pokemonViewModel: PokemonViewModel = viewModel()) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavItem.Pokemons.route) {
        composable(NavItem.Info.route) { PokemonInfoScreen(pokemonViewModel = pokemonViewModel, navController = navController) }
        composable(NavItem.Pokemons.route) { PokemonScreen(pokemonViewModel = pokemonViewModel, navController = navController) }
    }

}