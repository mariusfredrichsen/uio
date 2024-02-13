package no.uio.ifi.in2000.mafredri.oblig2.ui.party

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartyTopAppBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(text = "Party info" )
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.navigate("homescreen") }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}