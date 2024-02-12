package no.uio.ifi.in2000.mafredri.oblig2.ui

import no.uio.ifi.in2000.mafredri.oblig2.model.alpacas.PartyInfo
import no.uio.ifi.in2000.mafredri.oblig2.model.votes.PartyVote

data class AlpacaPartiesUIState(
    val partiesInfo: List<PartyInfo> = listOf(),
    val partiesVotes: List<PartyVote> = listOf()
)