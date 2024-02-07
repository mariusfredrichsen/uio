package no.uio.ifi.in2000.mafredri.oblig2

import android.content.Context
import android.graphics.drawable.Icon
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import no.uio.ifi.in2000.mafredri.oblig2.ui.home.HomeScreen
import no.uio.ifi.in2000.mafredri.oblig2.ui.party.PartyScreen
import no.uio.ifi.in2000.mafredri.oblig2.ui.theme.Mafredri_oblig2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mafredri_oblig2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    /*val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "homescreen") {
                        composable("homescreen") { HomeScreen(navController = navController) }
                        composable("partyscreen/{partyId}",
                            arguments = listOf(navArgument("partyId") { type = NavType.StringType; nullable = false } )
                        ) { navBackStackEntry ->
                            PartyScreen(navController = navController, partyId = navBackStackEntry.arguments?.getString("partyId")
                                .toString())
                        }
                    }*/
                    Text(text = isInternetAvailable(LocalContext.current).toString())
                }
            }
        }
    }
    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }
}
