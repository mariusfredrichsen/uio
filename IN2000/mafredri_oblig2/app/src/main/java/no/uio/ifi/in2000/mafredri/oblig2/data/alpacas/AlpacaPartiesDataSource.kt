package no.uio.ifi.in2000.mafredri.oblig2.data.alpacas

import android.util.Log
import io.ktor.client.call.body
import io.ktor.client.plugins.onDownload
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.runBlocking
import no.uio.ifi.in2000.mafredri.oblig2.data.AlpacaClient
import no.uio.ifi.in2000.mafredri.oblig2.model.alpacas.Parties
import no.uio.ifi.in2000.mafredri.oblig2.model.alpacas.PartyInfo


class AlpacaPartiesDataSource {
    private val url: String =
        "https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/obligatoriske-oppgaver/alpacaparties.json"

    fun fetchAlpacaData(): List<PartyInfo> {
        val parties: Parties
        runBlocking {
            val httpResponse: HttpResponse = AlpacaClient.client.get(url)
            parties = httpResponse.body()
        }
        return parties.parties
    }
}