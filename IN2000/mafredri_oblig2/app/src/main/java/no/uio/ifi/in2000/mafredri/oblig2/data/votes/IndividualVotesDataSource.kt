package no.uio.ifi.in2000.mafredri.oblig2.data.votes

import io.ktor.client.call.body
import io.ktor.client.plugins.onDownload
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.runBlocking
import no.uio.ifi.in2000.mafredri.oblig2.data.AlpacaClient
import no.uio.ifi.in2000.mafredri.oblig2.model.alpacas.Parties
import no.uio.ifi.in2000.mafredri.oblig2.model.alpacas.PartyInfo
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.District

fun fetchIndividualVotes(district: District): List<PartyInfo> {
    val parties: Parties
    runBlocking {
        val httpResponse: HttpResponse =
            AlpacaClient.client.get("https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/obligatoriske-oppgaver/district1.json") {
                onDownload { bytesSentTotal, contentLength ->
                    println("Received $bytesSentTotal bytes from $contentLength")
                }
            }
        parties = httpResponse.body()
    }
    return parties.parties
}