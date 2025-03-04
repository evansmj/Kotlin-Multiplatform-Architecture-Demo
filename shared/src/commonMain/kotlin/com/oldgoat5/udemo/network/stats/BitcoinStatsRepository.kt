package com.oldgoat5.udemo.network.stats

import com.oldgoat5.udemo.network.UDemoException
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * The repository can be used to return fresh network data, or data from the local cache.
 */
class BitcoinStatsRepository(
    private val statsRemoteDataSource: IBitcoinStatsRemoteDataSource
) : IBitcoinStatsRepository {
    private val statsMutex = Mutex()
    private var cachedStats: BitcoinStatsData? = null

    @NativeCoroutines
    override suspend fun getStats(refresh: Boolean): BitcoinStatsData {
        return try {
            if (refresh || cachedStats == null) {
                val networkResult = statsRemoteDataSource.fetchStats()
                statsMutex.withLock {
                    this.cachedStats = networkResult.data["1"]!!
                }
            }
            statsMutex.withLock { this.cachedStats!! }
        } catch (e: Exception) {
            throw UDemoException(this.cachedStats, e)
        }
    }
}
