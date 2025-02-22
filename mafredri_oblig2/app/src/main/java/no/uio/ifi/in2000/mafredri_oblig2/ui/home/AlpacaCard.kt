package no.uio.ifi.in2000.mafredri_oblig2.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import no.uio.ifi.in2000.mafredri_oblig2.model.parties.PartyInfo


@Composable
fun AlpacaCard(partyInfo: PartyInfo, navController: NavController) {
    Card(
        modifier = Modifier
                .padding(16.dp)
            .clickable {
                navController.navigate("partyscreen/${partyInfo.id}")
            }
    ) {
        AlpacaPoster(partyInfo = partyInfo)
    }
}

@Composable
fun AlpacaPoster(partyInfo: PartyInfo) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = partyInfo.name,
            textAlign = TextAlign.Center
        )
        AsyncImage(
            alignment = Alignment.Center,
            model = partyInfo.img,
            contentDescription = "Leader Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )
        Text(
            text = partyInfo.leader
        )
        Box(
            modifier = Modifier
                .background(Color(android.graphics.Color.parseColor(partyInfo.color)))
                .height(20.dp)
                .fillMaxWidth()
        )
    }
}