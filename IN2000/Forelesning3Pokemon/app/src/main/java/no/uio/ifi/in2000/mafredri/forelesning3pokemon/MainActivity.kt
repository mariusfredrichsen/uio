package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import no.uio.ifi.in2000.mafredri.forelesning3pokemon.ui.theme.Forelesning3PokemonTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Forelesning3PokemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val pvm: PokemonViewModel = viewModel()
                    val pivm: PokemonInfoViewModel = viewModel()

                    val scope = rememberCoroutineScope()
                    val snackbarHostState = remember { SnackbarHostState() }

                    Scaffold(
                        bottomBar = { NavBar(navController) },
                        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                    ) {
                        NavHost(navController = navController, startDestination = NavItem.Home.route) {
                            composable(NavItem.Home.route) { PokemonInfoScreen(pivm) }
                            composable(NavItem.Pokemons.route) { PokemonScreen(pvm) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NavBar(navController: NavController) {
    NavigationBar(

    ) {
        var selectedScreen by remember { mutableIntStateOf(0) } // Start skjerm er på 0

        listOf(NavItem.Home, NavItem.Pokemons).forEachIndexed { index, navItem ->
                NavigationBarItem(
                    selected = index == selectedScreen,
                    onClick = { selectedScreen = index; navController.navigate(navItem.route)},
                    icon = { Icon(imageVector = navItem.icon, contentDescription = null) })

        }
    }
}
