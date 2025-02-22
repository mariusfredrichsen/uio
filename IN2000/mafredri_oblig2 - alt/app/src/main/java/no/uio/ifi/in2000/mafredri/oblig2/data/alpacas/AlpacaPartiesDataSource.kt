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
import java.net.UnknownHostException


class AlpacaPartiesDataSource {
    private val url: String = "https://in2000-proxy.ifi.uio.no/alpacaapi/v2/alpacaparties"

    suspend fun fetchAlpacaData(): List<PartyInfo> {
        val parties: Parties

        runBlocking {
            parties = try {
                val httpResponse: HttpResponse = AlpacaClient.client.get(url)
                httpResponse.body()
            } catch (e: UnknownHostException) {
                println(e)
                Parties(listOf())
            }
        }
        return parties.parties

    }

}