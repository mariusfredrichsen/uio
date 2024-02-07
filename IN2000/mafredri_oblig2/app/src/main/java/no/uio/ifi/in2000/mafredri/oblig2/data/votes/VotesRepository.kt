package no.uio.ifi.in2000.mafredri.oblig2.data.votes

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import no.uio.ifi.in2000.mafredri.oblig2.model.alpacas.PartyInfo
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.District
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.DistrictVotes


class VotesRepository {
    private val _districtOneVotes = MutableStateFlow<List<DistrictVotes>>(listOf())
    private val _districtTwoVotes = MutableStateFlow<List<DistrictVotes>>(listOf())
    private val _districtThreeVotes = MutableStateFlow<List<DistrictVotes>>(listOf())
    fun loadDistrictOneVotes(): StateFlow<List<DistrictVotes>> = _districtOneVotes.asStateFlow()
    fun loadDistrictTwoVotes(): StateFlow<List<DistrictVotes>> = _districtTwoVotes.asStateFlow()
    fun loadDistrictThreeVotes(): StateFlow<List<DistrictVotes>> = _districtThreeVotes.asStateFlow()

    init {
        _districtOneVotes.update {
            fetchIndividualVotesOne()
        }
        _districtOneVotes.update {
            fetchIndividualVotesTwo()
        }
        _districtOneVotes.update {
            fetchAggregatedVotesThree()
        }
    }

    suspend fun getPartyVote(district: District, partyId: String) {

    }
}