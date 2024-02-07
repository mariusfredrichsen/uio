package no.uio.ifi.in2000.mafredri.oblig2.model.votes

import kotlinx.serialization.Serializable

data class DistrictVotes(
    val district: District,
    val alpacaPartyId: String,
    val numberOfVotesForParty: Int
)