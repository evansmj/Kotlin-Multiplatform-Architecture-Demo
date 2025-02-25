package com.oldgoat5.udemo.network.stats

interface IStatsRepository {
    suspend fun getStats(refresh: Boolean): StatsResponse
}
