package no.uio.ifi.in2000.mafredri.oblig2.ui.party

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PartyScreen(partyViewModel: PartyViewModel = viewModel(), partyId: String, navController: NavController) {
        Column {
            PartyTopAppBar(navController)
            val alpacaPartiesUIState by partyViewModel.alpacaPartiesUIState.collectAsState()

            partyViewModel.getPartyInfo(partyId)

            if (alpacaPartiesUIState.partiesInfo.size == 1) {

                LazyColumn {

                    items(alpacaPartiesUIState.partiesInfo) { partyInfo ->

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(android.graphics.Color.parseColor(partyInfo.color)))
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
                        Text(
                            text = partyInfo.description,
                            modifier = Modifier
                                .padding(4.dp)
                        )
                    }
                }
            }
        }
}