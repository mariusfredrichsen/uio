package no.uio.ifi.in2000.mafredri.forelesning3pokemon

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PokemonInfoScreen(navController: NavController) {
    val pokemonInfoRepository = PokemonInfoRepository()
    var showLoading by remember { mutableStateOf(false) }
    val scope  = rememberCoroutineScope()
    var text by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            NavBar(navController = navController)
        }
    ) {
        Column {
            if (showLoading) {
                LinearProgressIndicator( modifier = Modifier.fillMaxWidth())
            }
            Text(text = "asdasd$text")


            Button(
                onClick = {
                    scope.launch {
                        text = pokemonInfoRepository.getAllInfo()
                        showLoading = !showLoading
                    }
                }
            ) {
                Text(text = "TRYKK PÅ MEG AIUSHFIUAHSF")
            }

        }

    }

}