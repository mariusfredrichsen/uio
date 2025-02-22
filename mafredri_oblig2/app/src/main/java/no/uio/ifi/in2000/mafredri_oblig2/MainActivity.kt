package no.uio.ifi.in2000.mafredri_oblig2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import no.uio.ifi.in2000.mafredri_oblig2.ui.home.HomeScreen
import no.uio.ifi.in2000.mafredri_oblig2.ui.party.PartyScreen
import no.uio.ifi.in2000.mafredri_oblig2.ui.theme.Mafredri_oblig2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mafredri_oblig2Theme {
                AlpacaApp()
            }
        }
    }
}

@Composable
fun AlpacaApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homescreen") {
        composable("homescreen") { HomeScreen(navController = navController) }
        composable("partyscreen/{partyId}") { navBackStackEntry ->
            PartyScreen(navController = navController, partyId = navBackStackEntry.arguments?.getString("partyId")
                .toString())
        }
    }
}