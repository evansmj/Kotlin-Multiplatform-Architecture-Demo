package com.oldgoat5.udemo.network.stats

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.StateFlow

interface IBitcoinStatsInteractor {
    @NativeCoroutinesState
    val state: StateFlow<BitcoinStatsState>
    @NativeCoroutines
    suspend fun getStats(refresh: Boolean)
}
