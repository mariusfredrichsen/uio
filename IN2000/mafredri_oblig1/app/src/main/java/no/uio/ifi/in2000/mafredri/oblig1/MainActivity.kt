package no.uio.ifi.in2000.mafredri.oblig1

import android.os.Bundle
import android.provider.CalendarContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import no.uio.ifi.in2000.mafredri.oblig1.ui.palindrome.PalindromeScreen
import no.uio.ifi.in2000.mafredri.oblig1.ui.theme.Mafredri_oblig1Theme
import no.uio.ifi.in2000.mafredri.oblig1.ui.unitconverter.UnitConverterScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mafredri_oblig1Theme {
                val asd = rememberSystemUiController()
                SideEffect {
                    asd.setSystemBarsColor(Color(0xffd18700))
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val palindromeVM: PalindromeViewModel = viewModel()
                    val unitconverterVM: UnitConverterViewModel = viewModel()
                    HovedSide(palindromeVM, unitconverterVM)
                }
            }
        }
    }
}


@Composable
fun HovedSide(palindromeVM: PalindromeViewModel, unitconverterVM: UnitConverterViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "palindrome") {
        composable("palindrome") { PalindromeScreen(onNavigateToUnitConverter = { navController.navigate("unitconverter") }, palindromeVM)  }
        composable("unitconverter") { UnitConverterScreen(onNavigateToPalindrome = {navController.navigate("palindrome") }, unitconverterVM)  }
    }
}
