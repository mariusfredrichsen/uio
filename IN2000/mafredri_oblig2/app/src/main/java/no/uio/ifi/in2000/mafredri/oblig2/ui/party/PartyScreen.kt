package no.uio.ifi.in2000.mafredri.oblig2.ui.party

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PartyScreen(partyViewModel: PartyViewModel) {


    Scaffold {
        Column {
            Card(
                colors = CardDefaults.cardColors(Color(android.graphics.Color.parseColor(partyInfo.color))),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp)
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