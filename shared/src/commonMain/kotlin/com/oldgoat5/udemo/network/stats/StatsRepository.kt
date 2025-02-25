package com.oldgoat5.udemo.network.stats

import com.oldgoat5.udemo.network.UDemoException
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class StatsRepository(
    private val statsRemoteDataSource: IStatsRemoteDataSource
) : IStatsRepository {
    private val statsMutex = Mutex()
    private var cachedStats: StatsResponse? = null

    override suspend fun getStats(refresh: Boolean): StatsResponse {
        return try {
            if (refresh || cachedStats == null) {
                val networkResult = statsRemoteDataSource.fetchStats()
                statsMutex.withLock {
                    this.cachedStats = networkResult
                }
            }
            statsMutex.withLock { this.cachedStats!! } // can not be null
        } catch (e: Exception) {
            throw UDemoException(this.cachedStats, e)
        }
    }
}
