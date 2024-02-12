package no.uio.ifi.in2000.mafredri.oblig2.data

import kotlinx.coroutines.flow.MutableStateFlow
import no.uio.ifi.in2000.mafredri.oblig2.model.alpacas.PartyInfo
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.District
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.DistrictVotes

object AlpacaCache {

    val alpacaParties = MutableStateFlow<List<PartyInfo>>(listOf())

    val alpacaVotes = mutableMapOf<District, List<DistrictVotes>>()

}