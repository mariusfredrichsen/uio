package no.uio.ifi.in2000.mafredri.oblig2.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import no.uio.ifi.in2000.mafredri.oblig2.ui.party.PartyScreen

@Composable
fun NavigationController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homescreen") {
        composable("homescreen") { HomeScreen(navController = navController) }
        composable("partyscreen/{partyId}",
            arguments = listOf(navArgument("partyId") { type = NavType.StringType; nullable = false } )
        ) { navBackStackEntry ->
            PartyScreen(navController = navController, partyId = navBackStackEntry.arguments?.getString("partyId")
                .toString())
        }
    }
}