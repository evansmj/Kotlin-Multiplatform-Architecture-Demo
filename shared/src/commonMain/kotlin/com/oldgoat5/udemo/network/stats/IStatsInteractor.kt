package com.oldgoat5.udemo.network.stats

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.StateFlow

interface IStatsInteractor {
    @NativeCoroutinesState
    val state: StateFlow<StatsState>
    @NativeCoroutines
    suspend fun getStats(refresh: Boolean)
}
