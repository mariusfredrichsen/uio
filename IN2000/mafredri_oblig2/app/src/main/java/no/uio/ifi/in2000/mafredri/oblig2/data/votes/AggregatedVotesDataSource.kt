package no.uio.ifi.in2000.mafredri.oblig2.data.votes

import io.ktor.client.call.body
import io.ktor.client.plugins.onDownload
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.runBlocking
import no.uio.ifi.in2000.mafredri.oblig2.data.AlpacaClient
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.District
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.DistrictVotes
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.PartiesVote



class AggregatedVotesDataSource {

    private val url3 = "https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/obligatoriske-oppgaver/district3.json"
    fun fetchAggregatedVotesThree(): List<DistrictVotes> {
        val partiesVote: PartiesVote
        runBlocking {
            partiesVote = if (isConnected(url3)) {
                val httpResponse: HttpResponse = AlpacaClient.client.get(url3)
                httpResponse.body()
            } else {
                PartiesVote(listOf())
            }
        }
        return partiesVote.parties.map {
            DistrictVotes(District.THREE, it.partyId, it.votes)
        }
    }

    suspend fun isConnected(url: String): Boolean {
        return try {

            val httpResponse: HttpResponse = AlpacaClient.client.get(url)
            httpResponse.status.value in 200..299
        } catch (e: Exception) {
            false
        }
    }
}
