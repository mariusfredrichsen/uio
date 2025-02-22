package no.uio.ifi.in2000.mafredri_oblig2.data.votes

import io.ktor.client.call.body
import io.ktor.client.request.get
import no.uio.ifi.in2000.mafredri_oblig2.data.APIClient
import no.uio.ifi.in2000.mafredri_oblig2.model.votes.AggregatedVotes
import no.uio.ifi.in2000.mafredri_oblig2.model.votes.District
import no.uio.ifi.in2000.mafredri_oblig2.model.votes.DistrictVotes
import no.uio.ifi.in2000.mafredri_oblig2.model.votes.Vote
import java.net.UnknownHostException

class IndividualVotesDataSource {
    suspend fun getDistrictOneVotes(): List<DistrictVotes> {
        val url = "https://in2000-proxy.ifi.uio.no/alpacaapi/v2/district1"

        return try {
            val response = APIClient.client.get(url)
            if (response.status.value in 200..299) {
                val votes: List<Vote> = response.body()
                votes.groupBy { vote ->
                    vote.id
                }.map { pair ->
                    DistrictVotes(
                        district = District.ONE,
                        alpacaPartyId = pair.key,
                        numberOfVotesForParty = pair.value.size,
                    )
                }
            } else {
                listOf()
            }
        } catch (e: UnknownHostException) {
            listOf()
        }
    }

    suspend fun getDistrictTwoVotes(): List<DistrictVotes> {
        val url = "https://in2000-proxy.ifi.uio.no/alpacaapi/v2/district1"

        return try {
            val response = APIClient.client.get(url)
            if (response.status.value in 200..299) {
                val votes: List<Vote> = response.body()
                votes.groupBy { vote ->
                    vote.id
                }.map { pair ->
                    DistrictVotes(
                        district = District.TWO,
                        alpacaPartyId = pair.key,
                        numberOfVotesForParty = pair.value.size,
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