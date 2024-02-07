package no.uio.ifi.in2000.mafredri.oblig2.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextParams
import android.os.Build
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.mafredri.oblig2.data.AlpacaClient
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.District


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(homeScreenViewModel: HomeScreenViewModel = viewModel(), navController: NavController) {

    val alpacaPartiesUIState by homeScreenViewModel.alpacaPartiesUIState.collectAsState()

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var isExpanded by remember { mutableStateOf(false) }
    val districts: List<String> = listOf("District One", "District Two", "District Three")
    var selectedDistrict by remember { mutableStateOf("") }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        if (!AlpacaClient.isInternetAvailable(LocalContext.current)) {
            scope.launch {
                snackbarHostState
                    .showSnackbar(
                        message = "No internett connection",
                        duration = SnackbarDuration.Indefinite
                    )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {


                Text(text = "Partier", fontSize = 32.sp)
                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = it }
                ) {
                    OutlinedTextField(
                        value = selectedDistrict,
                        onValueChange = {},
                        placeholder = { Text(text = "Choose a district") },
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )
                    ExposedDropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false }
                    ) {
                        districts.filter { it != selectedDistrict }.forEach { district ->
                            DropdownMenuItem(
                                text = { Text(text = district) },
                                onClick = {
                                    selectedDistrict = district
                                    isExpanded = false
                                    when (districts.indexOf(selectedDistrict)) {
                                        0 -> homeScreenViewModel.getPartyVotes(District.ONE)
                                        1 -> homeScreenViewModel.getPartyVotes(District.TWO)
                                        2 -> homeScreenViewModel.getPartyVotes(District.THREE)
                                    }
                                }
                            )
                        }
                    }
                }
                VoteList(homeScreenViewModel)
                LazyColumn {
                    items(alpacaPartiesUIState.partiesInfo) { partyInfo ->

                        Card(
                            colors = CardDefaults.cardColors(
                                Color(
                                    android.graphics.Color.parseColor(
                                        partyInfo.color
                                    )
                                )
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 8.dp)
                                .clickable {
                                    navController.navigate("partyscreen/${partyInfo.id}")
                                }
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(text = partyInfo.name)

                                AsyncImage(
                                    model = partyInfo.img,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(shape = RoundedCornerShape(50))
                                        .size(200.dp),
                                    contentScale = ContentScale.Crop
                                )

                                Text(text = "Leder: " + partyInfo.leader)

                            }
                        }
                    }

                }
            }
        }
    }
}