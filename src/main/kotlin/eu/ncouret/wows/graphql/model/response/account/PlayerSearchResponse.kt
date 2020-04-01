package eu.ncouret.wows.graphql.model.response.account

import eu.ncouret.wows.graphql.model.ApiData

data class PlayerSearchResponse(val nickname: String, val accountId: String?): ApiData