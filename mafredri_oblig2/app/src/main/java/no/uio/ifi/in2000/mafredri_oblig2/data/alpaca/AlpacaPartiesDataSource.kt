package no.uio.ifi.in2000.mafredri_oblig2.data.alpaca

import io.ktor.client.call.body
import io.ktor.client.request.get
import no.uio.ifi.in2000.mafredri_oblig2.data.APIClient
import no.uio.ifi.in2000.mafredri_oblig2.model.parties.Parties
import no.uio.ifi.in2000.mafredri_oblig2.model.parties.PartyInfo
import java.net.UnknownHostException

class AlpacaPartiesDataSource {



    suspend fun getPartyInfo(): List<PartyInfo> {
        val url = "https://in2000-proxy.ifi.uio.no/alpacaapi/v2/alpacaparties"

        return try {
            val response = APIClient.client.get(url)
            if (response.status.value in 200..299) {
                val parties: Parties = response.body()
                return parties.parties
            } else {
                listOf()
            }
        } catch (e: UnknownHostException) {
            listOf()
        }
    }
}