package com.oldgoat5.udemo.network.stats

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesIgnore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeBitcoinStatsInteractor : IBitcoinStatsInteractor {
    private val _state = MutableStateFlow<BitcoinStatsState>(BitcoinStatsState.Loading)
    override val state: StateFlow<BitcoinStatsState> = _state

    @NativeCoroutinesIgnore
    suspend fun emitSuccess(data: BitcoinStatsData) {
        _state.emit(BitcoinStatsState.Success(data))
    }

    @NativeCoroutinesIgnore
    suspend fun emitError() {
        _state.emit(BitcoinStatsState.Error(null))
    }

    @NativeCoroutinesIgnore
    suspend fun emitLoading() {
        _state.emit(BitcoinStatsState.Loading)
    }

    override suspend fun getStats(refresh: Boolean) {
        // no-op
    }
}
