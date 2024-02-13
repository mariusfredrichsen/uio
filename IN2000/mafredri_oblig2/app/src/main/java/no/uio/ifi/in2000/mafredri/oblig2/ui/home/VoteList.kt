package no.uio.ifi.in2000.mafredri.oblig2.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun VoteList(homeScreenViewModel: HomeScreenViewModel) {
    val alpacaPartiesUIState by homeScreenViewModel.alpacaPartiesUIState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = "Parties", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Votes per party", fontWeight = FontWeight.Bold)
        }
        alpacaPartiesUIState.partiesVotes.forEach { district ->
            Row(
                modifier = Modifier
                    .padding(8.dp)
            )  {
                Text(text = district.name)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = district.votes.toString())
            }
        }
    }
}