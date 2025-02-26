package com.oldgoat5.udemo.network.stats

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines

interface IStatsRepository {
    @NativeCoroutines
    suspend fun getStats(refresh: Boolean): StatsResponse
}
