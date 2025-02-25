package com.oldgoat5.udemo.network.stats

interface IStatsRemoteDataSource {

    suspend fun fetchStats(): StatsResponse
}
