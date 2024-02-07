package no.uio.ifi.in2000.mafredri.oblig2.data.votes

import io.ktor.client.call.body
import io.ktor.client.plugins.onDownload
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.runBlocking
import no.uio.ifi.in2000.mafredri.oblig2.data.AlpacaClient
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.District
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.DistrictVotes
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.IndividualVote

fun fetchIndividualVotesOne(): List<DistrictVotes> {
    val individualVotes: List<IndividualVote>
    runBlocking {
        val httpResponse: HttpResponse =
            AlpacaClient.client.get("https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/obligatoriske-oppgaver/district1.json") {
                onDownload { bytesSentTotal, contentLength ->
                    println("Received $bytesSentTotal bytes from $contentLength")
                }
            }
        individualVotes = httpResponse.body()
    }
    return listOf(
        DistrictVotes(District.ONE, "1", individualVotes.count { it.id == "1" }),
        DistrictVotes(District.ONE, "2", individualVotes.count { it.id == "2" }),
        DistrictVotes(District.ONE, "3", individualVotes.count { it.id == "3" }),
        DistrictVotes(District.ONE, "4", individualVotes.count { it.id == "4" }),
    )

}

fun fetchIndividualVotesTwo(): List<DistrictVotes> {
    val individualVotes: List<IndividualVote>
    runBlocking {
        val httpResponse: HttpResponse =
            AlpacaClient.client.get("https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/obligatoriske-oppgaver/district2.json") {
                onDownload { bytesSentTotal, contentLength ->
                    println("Received $bytesSentTotal bytes from $contentLength")
                }
            }
        individualVotes = httpResponse.body()
    }
    return listOf(
        DistrictVotes(District.TWO, "1", individualVotes.count { it.id == "1" }),
        DistrictVotes(District.TWO, "2", individualVotes.count { it.id == "2" }),
        DistrictVotes(District.TWO, "3", individualVotes.count { it.id == "3" }),
        DistrictVotes(District.TWO, "4", individualVotes.count { it.id == "4" }),
    )

}


