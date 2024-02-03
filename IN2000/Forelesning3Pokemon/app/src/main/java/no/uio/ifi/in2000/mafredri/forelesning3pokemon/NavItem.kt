package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(val name: String, val route: String, val icon: ImageVector) {
    object Info : NavItem("Pokemon Info", "info", Icons.Rounded.Info)
    object Pokemons : NavItem("Pokemons", "pokemons", Icons.Rounded.List)
}