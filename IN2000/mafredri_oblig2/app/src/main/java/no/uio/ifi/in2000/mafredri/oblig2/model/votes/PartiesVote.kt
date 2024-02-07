package no.uio.ifi.in2000.mafredri.oblig2.model.votes

import kotlinx.serialization.Serializable

@Serializable
data class PartiesVote(val parties: List<AggregatedVote>)
