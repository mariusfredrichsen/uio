package no.uio.ifi.in2000.mafredri_oblig2.data.alpaca

import no.uio.ifi.in2000.mafredri_oblig2.data.votes.VotesRepository
import no.uio.ifi.in2000.mafredri_oblig2.model.parties.PartyInfo
import no.uio.ifi.in2000.mafredri_oblig2.model.votes.DistrictVotes

class AlpacaPartiesRepository {
    private val _alpacaPartiesDataSource = AlpacaPartiesDataSource()
    private val _votesRepository = VotesRepository()

    suspend fun getPartyInfo(): List<PartyInfo> {
        return _alpacaPartiesDataSource.getPartyInfo()
    }

    suspend fun getParty(partyId: String): PartyInfo? {
        val parties = _alpacaPartiesDataSource.getPartyInfo()
        return parties.firstOrNull { partyInfo ->
            partyInfo.id == partyId
        }
    }

    suspend fun getVotes(partyId: String): List<DistrictVotes> {
        return _votesRepository.getVotes(partyId = partyId)
    }
}