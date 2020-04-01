package eu.ncouret.wows.graphql.model


data class ListApiResponse<out T : ApiData>(val meta: ApiMetadata, val status: String,  val data: List<T>)