package no.uio.ifi.in2000.mafredri.jokes.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val jokesUIState by homeViewModel.jokesUIState.collectAsState()

    Column {
        Text(text = "MASSE KULE VITSER HAHAHAHAHAHAHAHA")
        Text(text = jokesUIState.jokes.size.toString())
        LazyColumn {
            items(jokesUIState.jokes) {joke ->
                Card {
                    Column {
                        Text(text = joke.id)
                        Text(text = joke.joke)
                    }
                }
            }
        }
    }
}