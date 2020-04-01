package eu.ncouret.wows.graphql.client.account

import com.fasterxml.jackson.databind.ObjectMapper
import eu.ncouret.wows.graphql.model.ApiData
import eu.ncouret.wows.graphql.model.ListApiResponse
import eu.ncouret.wows.graphql.model.MapApiResponse
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class ResponseMapper(val mapper: ObjectMapper) {

    fun <T : ApiData> listResponse(response: String, clazz: KClass<T>): List<T> =
        mapper.readValue<ListApiResponse<T>>(
            response,
            mapper.typeFactory.constructParametricType(ListApiResponse::class.java, clazz.java)
        ).data

    fun <T : ApiData> mapResponse(response: String, clazz: KClass<T>): List<T> = mapper.readValue<MapApiResponse<T>>(
        response,
        mapper.typeFactory.constructParametricType(MapApiResponse::class.java, clazz.java)
    ).data.values.toList()
}