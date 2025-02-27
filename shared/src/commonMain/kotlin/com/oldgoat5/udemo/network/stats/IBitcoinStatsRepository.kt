package com.oldgoat5.udemo.network.stats

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines

interface IBitcoinStatsRepository {
    @NativeCoroutines
    suspend fun getStats(refresh: Boolean): BitcoinStatsData
}
