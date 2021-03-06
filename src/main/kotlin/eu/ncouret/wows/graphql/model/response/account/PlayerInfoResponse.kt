package eu.ncouret.wows.graphql.model.response.account

import eu.ncouret.wows.graphql.model.ApiData

data class PlayerInfoResponse(val accountId: String,
                              val createdAt: Int,
                              val hiddenProfile: Boolean,
                              val lastBattleTime: Int,
                              val levelingPoints: Int,
                              val levelingTier: Int,
                              val logoutAt: Int,
                              val nickname: String) : ApiData