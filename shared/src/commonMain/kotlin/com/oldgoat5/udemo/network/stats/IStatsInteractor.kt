package com.oldgoat5.udemo.network.stats

import kotlinx.coroutines.flow.StateFlow

interface IStatsInteractor {
    val state: StateFlow<StatsState>
    suspend fun getStats(refresh: Boolean)
}
