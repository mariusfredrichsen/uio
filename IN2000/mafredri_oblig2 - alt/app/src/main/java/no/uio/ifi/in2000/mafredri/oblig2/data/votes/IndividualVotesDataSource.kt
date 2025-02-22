package no.uio.ifi.in2000.mafredri.oblig2.data.votes

import android.util.Log
import io.ktor.client.call.body
import io.ktor.client.plugins.onDownload
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.runBlocking
import no.uio.ifi.in2000.mafredri.oblig2.data.AlpacaClient
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.District
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.DistrictVotes
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.IndividualVote
import java.net.UnknownHostException

class IndividualVotesDataSource {
    private val url1 = "https://in2000-proxy.ifi.uio.no/alpacaapi/v2/district1"
    private val url2 = "https://in2000-proxy.ifi.uio.no/alpacaapi/v2/district2"

    suspend fun fetchIndividualVotesOne(): List<DistrictVotes> {
        val individualVotes: List<IndividualVote>
        runBlocking {
            individualVotes = try {
                val httpResponse: HttpResponse = AlpacaClient.client.get(url1)
                httpResponse.body()
            } catch (e: UnknownHostException ){
                listOf()
            }
        }
        return listOf("1", "2", "3", "4").map {id ->
            DistrictVotes(District.ONE, id, individualVotes.count { it.id == id })
        }

    }

    suspend fun fetchIndividualVotesTwo(): List<DistrictVotes> {
        val individualVotes: List<IndividualVote>
        runBlocking {
            individualVotes = try {
                val httpResponse: HttpResponse = AlpacaClient.client.get(url2)
                httpResponse.body()
            } catch (e: UnknownHostException) {
                listOf()
            }
        }
        return listOf("1", "2", "3", "4").map {id ->
            DistrictVotes(District.TWO, id, individualVotes.count { it.id == id })
        }

    }
}