package no.uio.ifi.in2000.mafredri.oblig2.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.lang.Math.random
import kotlin.math.roundToInt

@Preview
@Composable
fun VoteList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = "Parti", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Antall stemmer", fontWeight = FontWeight.Bold)
        }
        listOf("AlpacaNorth", "AlpacaSouth", "AlpacaWest", "AlpacaWest").forEach { district ->
            Row(
                modifier = Modifier
                    .padding(8.dp)
            )  {
                Text(text = district)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = (random()*10000).roundToInt().toString())
            }
        }
    }
}