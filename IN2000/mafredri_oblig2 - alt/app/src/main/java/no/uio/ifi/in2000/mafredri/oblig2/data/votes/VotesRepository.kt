package no.uio.ifi.in2000.mafredri.oblig2.data.votes

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import no.uio.ifi.in2000.mafredri.oblig2.data.AlpacaCache
import no.uio.ifi.in2000.mafredri.oblig2.model.alpacas.PartyInfo
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.District
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.DistrictVotes


class VotesRepository {
    private val individualVotesDataSource = IndividualVotesDataSource()
    private val aggregatedVotesDataSource = AggregatedVotesDataSource()

    private val _districtOneVotes = MutableStateFlow<List<DistrictVotes>>(listOf())
    private val _districtTwoVotes = MutableStateFlow<List<DistrictVotes>>(listOf())
    private val _districtThreeVotes = MutableStateFlow<List<DistrictVotes>>(listOf())

    suspend fun getPartyVotes(district: District) {
        val districtVotes = AlpacaCache.alpacaVotes[district]

        when (district) {
            District.ONE -> {
                if (districtVotes != null) {
                    _districtOneVotes.update {
                        districtVotes
                    }
                } else {
                    val fetchedData = individualVotesDataSource.fetchIndividualVotesOne()
                    _districtOneVotes.update {
                        fetchedData
                    }
                    AlpacaCache.alpacaVotes[district] = fetchedData
                }
            }
            District.TWO -> {
                if (districtVotes != null) {
                    _districtTwoVotes.update {
                        districtVotes
                    }
                } else {
                    val fetchedData = individualVotesDataSource.fetchIndividualVotesTwo()
                    _districtTwoVotes.update {
                        fetchedData
                    }
                    AlpacaCache.alpacaVotes[district] = fetchedData
                }
            }

            District.THREE -> {
                if (districtVotes != null) {
                    _districtThreeVotes.update {
                        districtVotes
                    }
                } else {
                    val fetchedData = aggregatedVotesDataSource.fetchAggregatedVotesThree()
                    _districtThreeVotes.update {
                        fetchedData
                    }
                    AlpacaCache.alpacaVotes[district] = fetchedData
                }
            }
            District.ALL -> {
                getPartyVotes(District.ONE)
                getPartyVotes(District.TWO)
                getPartyVotes(District.THREE)
            }
        }



    }

    suspend fun getDistrictVotes(district: District): List<DistrictVotes> = when(district) {
            District.ONE -> _districtOneVotes.value
            District.TWO -> _districtTwoVotes.value
            District.THREE -> _districtThreeVotes.value
            District.ALL -> listOf(_districtOneVotes, _districtTwoVotes, _districtThreeVotes).flatMap { it.value }.groupBy { it.alpacaPartyId }.map { party -> DistrictVotes(District.ALL, party.value.first().alpacaPartyId, party.value.sumOf { it.numberOfVotesForParty }) }
    }
}