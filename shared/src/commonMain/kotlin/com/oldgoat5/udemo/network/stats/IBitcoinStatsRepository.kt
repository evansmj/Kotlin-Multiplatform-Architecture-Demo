package com.oldgoat5.udemo.network.stats

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines

/**
 * The repository can be used to return fresh network data, or data from the local cache.
 */
interface IBitcoinStatsRepository {
    @NativeCoroutines
    suspend fun getStats(refresh: Boolean): BitcoinStatsData
}
