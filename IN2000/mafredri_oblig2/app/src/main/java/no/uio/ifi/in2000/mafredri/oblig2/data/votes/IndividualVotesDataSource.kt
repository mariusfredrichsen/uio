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

class IndividualVotesDataSource {
    private val url1 = "https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/obligatoriske-oppgaver/district1.json"
    private val url2 = "https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/obligatoriske-oppgaver/district2.json"

    fun fetchIndividualVotesOne(): List<DistrictVotes> {
        val individualVotes: List<IndividualVote>
        runBlocking {
            individualVotes = if (isConnected(url1)) {
                val httpResponse: HttpResponse = AlpacaClient.client.get(url1)
                httpResponse.body()
            } else {
                listOf()
            }
        }
        return listOf("1", "2", "3", "4").map {id ->
            DistrictVotes(District.ONE, id, individualVotes.count { it.id == id })
        }

    }

    fun fetchIndividualVotesTwo(): List<DistrictVotes> {
        val individualVotes: List<IndividualVote>
        runBlocking {
            individualVotes = if (isConnected(url2)) {
                val httpResponse: HttpResponse = AlpacaClient.client.get(url2)
                httpResponse.body()
            } else {
                listOf()
            }
        }
        return listOf("1", "2", "3", "4").map {id ->
            DistrictVotes(District.TWO, id, individualVotes.count { it.id == id })
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