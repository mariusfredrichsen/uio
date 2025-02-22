package no.uio.ifi.in2000.mafredri_oblig2.ui.party

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartyScreenTopBar(
    navController: NavController,
    partyScreenViewModel: PartyScreenViewModel
) {
    val partyScreenUiState by partyScreenViewModel.partyScreenUIState.collectAsState()

    TopAppBar(
        title = {
            Text(
                text = partyScreenUiState.party?.name?:"Loading"
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Sharp.ArrowBack,
                    contentDescription = "Back Button")
            }
        }
    )
}