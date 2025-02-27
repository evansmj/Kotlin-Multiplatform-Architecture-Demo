package com.oldgoat5.udemo.network.stats

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines

interface IBitcoinStatsRemoteDataSource {

    @NativeCoroutines
    suspend fun fetchStats(): BitcoinStatsResponse
}
