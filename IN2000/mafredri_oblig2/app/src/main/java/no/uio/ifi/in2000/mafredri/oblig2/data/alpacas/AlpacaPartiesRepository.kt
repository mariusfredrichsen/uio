package no.uio.ifi.in2000.mafredri.oblig2.data.alpacas

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import no.uio.ifi.in2000.mafredri.oblig2.data.votes.VotesRepository
import no.uio.ifi.in2000.mafredri.oblig2.model.alpacas.PartyInfo
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.District
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.PartyVote

class AlpacaPartiesRepository {
    val votesRepository = VotesRepository()

    private val _partiesInfo = MutableStateFlow<List<PartyInfo>>(listOf())
    private val _partiesVotes = MutableStateFlow<List<PartyVote>>(listOf())
    fun loadPartiesInfo(): StateFlow<List<PartyInfo>> = _partiesInfo.asStateFlow()
    fun loadPartiesVotes(): StateFlow<List<PartyVote>> = _partiesVotes.asStateFlow()

    suspend fun getPartyInfo(id: String) {
        _partiesInfo.update { it ->
            it.filter { it.id == id }
        }
    }

    suspend fun getPartyVotes(district: District) {
        _partiesVotes.update {
            votesRepository.getDistrictVotes(district)
                .map { districtVote ->
                    PartyVote(_partiesInfo.value.first { partyInfo ->
                        partyInfo.id == districtVote.alpacaPartyId
                    }.name, districtVote.numberOfVotesForParty)

                }
        }
    }

    init {
        _partiesInfo.update {
            fetchAlpacaData()
        }
    }
}