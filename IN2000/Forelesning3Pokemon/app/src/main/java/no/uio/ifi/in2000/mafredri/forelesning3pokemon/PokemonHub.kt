package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Composable
fun PokemonHub(pokemonViewModel: PokemonViewModel = viewModel()) {


    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavItem.Pokemons.route) {
        composable(NavItem.Info.route) { PokemonInfoScreen(pokemonViewModel = pokemonViewModel, navController = navController) }
        composable(NavItem.Pokemons.route) { PokemonScreen(pokemonViewModel = pokemonViewModel, navController = navController) }
    }

}