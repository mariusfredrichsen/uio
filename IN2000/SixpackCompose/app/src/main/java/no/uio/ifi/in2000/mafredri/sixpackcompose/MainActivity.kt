package no.uio.ifi.in2000.mafredri.sixpackcompose

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import no.uio.ifi.in2000.mafredri.sixpackcompose.ui.theme.SixpackComposeTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SixpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationHost(navController)
                }
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {
    var selectedItem by remember { mutableIntStateOf(0) }
    Scaffold(
        bottomBar = {
            NavigationBar {
                BottomBarItem::class.nestedClasses.map {it.objectInstance as BottomBarItem}.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index; navController.navigate(item.route) },
                        icon = { androidx.compose.material3.Icon(
                            imageVector = item.icon,
                            contentDescription = null
                        ) },
                        label = { Text(text = item.label) }
                    )
                }
            }
        }
    ) {pVal ->
        Text(
            modifier = Modifier.padding(pVal),
            text = "SIUUU"
        )
    }

}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController, startDestination = BottomBarItem.Home.route) {
        composable(BottomBarItem.Home.route) { HomeScreen(navController) }
        composable(BottomBarItem.Workouts.route) { WorkoutsScreen(navController) }
        composable(BottomBarItem.CreateWorkout.route) { CreateWorkoutScreen(navController) }
    }
}