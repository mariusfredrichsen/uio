package no.uio.ifi.in2000.mafredri_oblig2.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import no.uio.ifi.in2000.mafredri_oblig2.model.parties.PartyInfo
import no.uio.ifi.in2000.mafredri_oblig2.model.votes.DistrictVotes


@Composable
fun VoteList(districtVotes: List<DistrictVotes>, parties: List<PartyInfo>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Parti")
            Text(text = "Antall stemmer")
        }
        districtVotes.forEach { districtVotes ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = parties.first {it.id == districtVotes.alpacaPartyId}.name)
                Text(text = districtVotes.numberOfVotesForParty.toString()
                )
            }
        }
    }
}