package no.uio.ifi.in2000.mafredri_oblig2.data.votes

import no.uio.ifi.in2000.mafredri_oblig2.model.votes.DistrictVotes

class VotesRepository {
    private val _aggregatedVotesDataSource = AggregatedVotesDataSource()
    private val _individualVotesDataSource = IndividualVotesDataSource()

    suspend fun getVotes(partyId: String): List<DistrictVotes> {
        return when(partyId) {
            "1" -> _individualVotesDataSource.getDistrictOneVotes()
            "2" -> _individualVotesDataSource.getDistrictTwoVotes()
            "3" -> _aggregatedVotesDataSource.getDistrictThreeVotes()
            else -> listOf()
        }
    }
}