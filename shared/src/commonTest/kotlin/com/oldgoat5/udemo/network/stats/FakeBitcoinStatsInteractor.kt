package com.oldgoat5.udemo.network.stats

import com.oldgoat5.udemo.network.user.IUserDataInteractor
import com.oldgoat5.udemo.network.user.UserDataResponse
import com.oldgoat5.udemo.network.user.UserDataState
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

    override suspend fun getStats(forceRefresh: Boolean) {
        // no-op
    }
}
