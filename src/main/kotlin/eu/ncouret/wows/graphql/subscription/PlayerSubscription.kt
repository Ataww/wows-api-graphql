package eu.ncouret.wows.graphql.subscription

import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.spring.operations.Subscription
import eu.ncouret.wows.graphql.client.account.ResponseMapper
import eu.ncouret.wows.graphql.client.account.WargamingApiClient
import eu.ncouret.wows.graphql.model.request.account.PlayerInfoParameters
import eu.ncouret.wows.graphql.model.response.account.PlayerInfoResponse
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.asPublisher
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.time.Duration

@Component
class PlayerSubscription(val mapper: ResponseMapper, val client: WargamingApiClient) : Subscription {

    @GraphQLDescription("Fetch the player every 5 seconds")
    suspend fun player(params: PlayerInfoParameters) =
        Flux.interval(Duration.ofSeconds(5)).asFlow().map {
            val response = client.sendQuery("/account/info/", mapOf(Pair("account_id", params.accountId)))
            mapper.mapResponse(response, PlayerInfoResponse::class)
        }.asPublisher()
}