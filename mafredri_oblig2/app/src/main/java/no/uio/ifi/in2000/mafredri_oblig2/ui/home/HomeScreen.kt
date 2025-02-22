package no.uio.ifi.in2000.mafredri_oblig2.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeScreenViewModel = viewModel(), navController: NavController) {
    val homeUIState by homeViewModel.homeUIState.collectAsState()
    homeViewModel.updateParties()
    homeViewModel.updateVotes("1")

    var expanded by remember {mutableStateOf(false)}

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            if (homeUIState.parties.isNotEmpty() && homeUIState.votes.isNotEmpty()) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded } ,
                ) {
                    Ex
                }
                VoteList(
                    districtVotes = homeUIState.votes,
                    parties = homeUIState.parties
                )
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                ) {
                    items(homeUIState.parties) { partyInfo ->
                        AlpacaCard(
                            navController = navController,
                            partyInfo = partyInfo
                        )
                    }
                }
            }
        }
    }
}