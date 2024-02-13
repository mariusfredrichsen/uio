package no.uio.ifi.in2000.mafredri.oblig2.data.votes

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.District
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.DistrictVotes


class VotesRepository {
    private val individualVotesDataSource = IndividualVotesDataSource()
    private val aggregatedVotesDataSource = AggregatedVotesDataSource()

    private val _districtOneVotes = MutableStateFlow<List<DistrictVotes>>(listOf())
    private val _districtTwoVotes = MutableStateFlow<List<DistrictVotes>>(listOf())
    private val _districtThreeVotes = MutableStateFlow<List<DistrictVotes>>(listOf())

    fun getPartiesVotes() {
        _districtOneVotes.update {
            individualVotesDataSource.fetchIndividualVotesOne()
        }
        _districtTwoVotes.update {
            individualVotesDataSource.fetchIndividualVotesTwo()
        }
        _districtThreeVotes.update {
            aggregatedVotesDataSource.fetchAggregatedVotesThree()
        }
    }

    fun getDistrictVotes(district: District): List<DistrictVotes> = when(district) {
            District.ONE -> _districtOneVotes.value
            District.TWO -> _districtTwoVotes.value
            District.THREE -> _districtThreeVotes.value
    }
}