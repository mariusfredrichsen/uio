package no.uio.ifi.in2000.mafredri.oblig2.model.votes

import kotlinx.serialization.Serializable

@Serializable
class AggregatedVote(
    val partyId: String,
    val votes: Int
)