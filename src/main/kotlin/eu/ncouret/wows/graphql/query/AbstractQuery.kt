package eu.ncouret.wows.graphql.query

import com.expediagroup.graphql.annotations.GraphQLIgnore
import com.expediagroup.graphql.spring.operations.Query
import eu.ncouret.wows.graphql.client.account.ResponseMapper
import eu.ncouret.wows.graphql.client.account.WargamingApiClient
import eu.ncouret.wows.graphql.model.ApiData
import eu.ncouret.wows.graphql.model.EnumValue
import kotlin.reflect.KClass

abstract class AbstractQuery(private val client: WargamingApiClient, private val mapper: ResponseMapper) : Query {

    @GraphQLIgnore
    suspend fun <T : ApiData> searchMap(url: String, params: Map<String, Any?>, clazz: KClass<T>): List<T> =
        mapper.mapResponse(client.sendQuery(url, normalizeParams(params)), clazz)

    @GraphQLIgnore
    suspend fun <T : ApiData> searchList(
        url: String,
        params: Map<String, Any?>,
        clazz: KClass<T>
    ): List<T> =
        mapper.listResponse(client.sendQuery(url, normalizeParams(params)), clazz)

    private fun normalizeParams(rawParams: Map<String, Any?>): Map<String, String> {
        val params = mutableMapOf<String, String>()
        rawParams.filterValues { it != null }.forEach { (key, value) ->
            params[key] = when (value) {
                is Map<*, *> -> value.asIterable().filterNot { it.key == null }
                    .joinToString(",") { (key, value) ->
                        "${normalizeParam(key)}=${normalizeParam(value)}"
                    }
                is List<*> -> value.filterNotNull()
                    .joinToString(",") { normalizeParam(it) }
                else -> normalizeParam(value)
            }
        }
        return params
    }

    private fun normalizeParam(param: Any?): String = when (param) {
        null -> ""
        is String -> param
        is EnumValue -> param.getValue()
        else -> param.toString()
    }
}

suspend inline fun <reified T : ApiData> AbstractQuery.doSearchList(url: String, params: Map<String, Any?>): List<T> =
    searchList(url, params, T::class)

suspend inline fun <reified T : ApiData> AbstractQuery.doSearchMap(url: String, params: Map<String, Any?>): List<T> =
    searchMap(url, params, T::class)