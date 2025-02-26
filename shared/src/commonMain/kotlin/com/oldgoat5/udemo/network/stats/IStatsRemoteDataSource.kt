package com.oldgoat5.udemo.network.stats

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines

interface IStatsRemoteDataSource {

    @NativeCoroutines
    suspend fun fetchStats(): StatsResponse
}
