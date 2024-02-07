package no.uio.ifi.in2000.mafredri.oblig2.ui.party

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import no.uio.ifi.in2000.mafredri.oblig2.model.alpacas.PartyInfo


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PartyScreen(partyViewModel: PartyViewModel = viewModel(), partyId: String, navController: NavController) {
    val alpacaPartiesUIState by partyViewModel.alpacaPartiesUIState.collectAsState()
    partyViewModel.getPartyInfo(partyId)

    val partyInfo: PartyInfo? = alpacaPartiesUIState.parties.firstOrNull()

    Column {
        PartyTopAppBar(navController)
        if (partyInfo != null) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
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
                            model = partyInfo?.img,
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
            Text(
                text = partyInfo.description,
                modifier = Modifier
                    .padding(4.dp)
                    .verticalScroll(rememberScrollState())
            )
        }
    }
}