package eu.ncouret.wows.graphql.model.request.account

import eu.ncouret.wows.graphql.model.Language

data class PlayerInfoParameters(
    val accountId: String,
    val fields: List<String>? = listOf(),
    val language: Language? = Language.EN
)