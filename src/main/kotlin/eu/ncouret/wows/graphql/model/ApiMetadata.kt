package eu.ncouret.wows.graphql.model

data class ApiMetadata(
    val count: Int,
    val pageTotal: Int? = null,
    val total: Int? = null,
    val limit: Int? = null,
    val page: Int? = null
)