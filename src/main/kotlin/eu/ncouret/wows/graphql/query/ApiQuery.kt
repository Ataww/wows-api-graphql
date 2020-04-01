package eu.ncouret.wows.graphql.query

import com.expediagroup.graphql.annotations.GraphQLDescription
import eu.ncouret.wows.graphql.client.account.ResponseMapper
import eu.ncouret.wows.graphql.client.account.WargamingApiClient
import eu.ncouret.wows.graphql.model.request.account.PlayerInfoParameters
import eu.ncouret.wows.graphql.model.request.account.SearchType
import eu.ncouret.wows.graphql.model.response.account.PlayerInfoResponse
import eu.ncouret.wows.graphql.model.response.account.PlayerSearchResponse
import org.springframework.stereotype.Component

@Component
class ApiQuery(client: WargamingApiClient, mapper: ResponseMapper) : AbstractQuery(client, mapper) {

    @GraphQLDescription("Query the Wargaming API to search for players' account")
    suspend fun searchPlayer(search: String, type: SearchType = SearchType.STARTS_WITH) =
        doSearchList<PlayerSearchResponse>(
            "/account/list/",
            mapOf(Pair("search", search), Pair("type", type))
        )

    @GraphQLDescription("Query the Wargaming API for the information of a certain player")
    suspend fun playerInfo(
        parameters: PlayerInfoParameters
    ) =
        doSearchMap<PlayerInfoResponse>(
            "/account/info/",
            mapOf(
                Pair("account_id", parameters.accountId),
                Pair("fields", parameters.fields),
                Pair("language", parameters.language)
            )
        )


}