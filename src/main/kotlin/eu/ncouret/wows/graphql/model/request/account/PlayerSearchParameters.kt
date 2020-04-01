package eu.ncouret.wows.graphql.model.request.account

import eu.ncouret.wows.graphql.model.ApiData

data class PlayerSearchParameters(val search: String, val type: SearchType? = SearchType.STARTS_WITH): ApiData