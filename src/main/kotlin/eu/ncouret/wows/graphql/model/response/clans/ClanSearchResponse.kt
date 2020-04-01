package eu.ncouret.wows.graphql.model.response.clans

import eu.ncouret.wows.graphql.model.ApiData

data class ClanSearchResponse(
    val clanId: Int,
    val createdAt: Int?,
    val membersCount: Int,
    val name: String,
    val tag: String
) : ApiData