package eu.ncouret.wows.graphql.model

open class ApiRequest(val fields: List<String> = listOf(), val language: Language = Language.EN)