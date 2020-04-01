package eu.ncouret.wows.graphql.model


data class MapApiResponse<out T : ApiData>(val meta: ApiMetadata, val status: String, val data: Map<String, T>)