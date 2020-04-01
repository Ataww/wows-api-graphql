package eu.ncouret.wows.graphql.query

import com.expediagroup.graphql.annotations.GraphQLDescription
import eu.ncouret.wows.graphql.client.account.ResponseMapper
import eu.ncouret.wows.graphql.client.account.WargamingApiClient
import eu.ncouret.wows.graphql.model.Language
import eu.ncouret.wows.graphql.model.response.clans.ClanSearchResponse
import org.springframework.stereotype.Component

@Component
class ClanQuery(client: WargamingApiClient, mapper: ResponseMapper) : AbstractQuery(client, mapper) {

    @GraphQLDescription("Query the Wargaming API to search for clans")
    suspend fun searchClan(search: String, language: Language? = Language.EN, limit: Int? = 100, pageNumber: Int? = 1, fields: List<String>? = listOf()) =
        doSearchList<ClanSearchResponse>(
            "/clans/list/",
            mapOf(
                Pair("search", search),
                Pair("language", language),
                Pair("limit", limit),
                Pair("page_no", pageNumber),
                Pair("fields", fields)
            )
        )
}