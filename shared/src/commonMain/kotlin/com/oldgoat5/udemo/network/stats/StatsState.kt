package com.oldgoat5.udemo.network.stats

sealed class StatsState {
    data object None: StatsState()
    data object Loading: StatsState()
    class Error(val cachedStats: StatsResponse?) : StatsState()
    class Success(val stats: StatsResponse) : StatsState()
}
