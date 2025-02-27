package com.oldgoat5.udemo.network.stats

sealed interface BitcoinStatsState {
    data object None: BitcoinStatsState
    data object Loading: BitcoinStatsState
    class Error(val cachedStats: BitcoinStatsData?) : BitcoinStatsState
    class Success(val stats: BitcoinStatsData) : BitcoinStatsState
}
