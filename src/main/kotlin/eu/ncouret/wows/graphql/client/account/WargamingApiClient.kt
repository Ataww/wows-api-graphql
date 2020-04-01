package eu.ncouret.wows.graphql.client.account

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange


@Service
class WargamingApiClient(
    @Value("\${application.id}") val applicationId: String,
    val client: WebClient
) {

    private val logger = LoggerFactory.getLogger(WargamingApiClient::class.java)

    /**
     * Send a query to the Wargaming API and retrieve the result as a string to be processed later
     */
    suspend fun sendQuery(
        url: String,
        parameters: Map<String, String>
    ): String =
        client.get().uri { builder ->
            builder.path(url)
            builder.queryParam("application_id", applicationId)
            parameters.forEach { builder.queryParam(it.key, it.value) }
            val uri = builder.build()
            logger.debug("Querying $uri")
            uri
        }.accept(MediaType.APPLICATION_JSON)
            .awaitExchange()
            .awaitBody()
}