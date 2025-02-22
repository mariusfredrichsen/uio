package no.uio.ifi.in2000.mafredri_oblig2.ui.party

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import no.uio.ifi.in2000.mafredri_oblig2.ui.home.AlpacaPoster


@Composable
fun PartyScreen(navController: NavController, partyScreenViewModel: PartyScreenViewModel = viewModel(), partyId: String) {
    val partyScreenUIState by partyScreenViewModel.partyScreenUIState.collectAsState()
    partyScreenViewModel.getParty(partyId = partyId)
    Scaffold(
        topBar = {
            PartyScreenTopBar(
                navController = navController,
                partyScreenViewModel = partyScreenViewModel
            )
        }
    ) { innerPadding ->
        if (
            partyScreenUIState.party != null
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 4.dp)
            ) {
                item {
                    AlpacaPoster(partyScreenUIState.party!!)
                }
                item {
                    Text(
                        text = partyScreenUIState.party!!.description
                    )
                }
            }
        }
    }
}