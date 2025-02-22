package no.uio.ifi.in2000.mafredri_oblig2.data.votes

import io.ktor.client.call.body
import io.ktor.client.request.get
import no.uio.ifi.in2000.mafredri_oblig2.data.APIClient
import no.uio.ifi.in2000.mafredri_oblig2.model.parties.Parties
import no.uio.ifi.in2000.mafredri_oblig2.model.votes.AggregatedVotes
import no.uio.ifi.in2000.mafredri_oblig2.model.votes.District
import no.uio.ifi.in2000.mafredri_oblig2.model.votes.DistrictVotes
import java.net.UnknownHostException

class AggregatedVotesDataSource {



    suspend fun getDistrictThreeVotes(): List<DistrictVotes> {
        val url = "https://in2000-proxy.ifi.uio.no/alpacaapi/v2/district3"

        return try {
            val response = APIClient.client.get(url)
            if (response.status.value in 200..299) {
                val votes: AggregatedVotes = response.body()
                votes.parties.map { districtVotes ->
                    DistrictVotes(
                        district = District.THREE,
                        alpacaPartyId = districtVotes.partyId,
                        numberOfVotesForParty = districtVotes.votes,
                    )
                }
            } else {
                listOf()
            }
        } catch (e: UnknownHostException) {
            listOf()
        }
    }
}