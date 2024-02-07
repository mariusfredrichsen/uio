package no.uio.ifi.in2000.mafredri.oblig2.model.votes

import no.uio.ifi.in2000.mafredri.oblig2.model.alpacas.PartyInfo

data class PartyVote(
    val name: String,
    val votes: Int
)