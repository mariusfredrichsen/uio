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
import java.net.UnknownHostException


class AggregatedVotesDataSource {

    private val url3 = "https://in2000-proxy.ifi.uio.no/alpacaapi/v2/district3"
    suspend fun fetchAggregatedVotesThree(): List<DistrictVotes> {
        val partiesVote: PartiesVote
        runBlocking {
            partiesVote = try {
                val httpResponse: HttpResponse = AlpacaClient.client.get(url3)
                httpResponse.body()
            } catch (e: UnknownHostException) {
                PartiesVote(listOf())
            }
        }
        return partiesVote.parties.map {
            DistrictVotes(District.THREE, it.partyId, it.votes)
        }
    }
}
