package com.oldgoat5.udemo.network.stats

import com.oldgoat5.udemo.network.UDemoException
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class BitcoinStatsInteractor(
    private val statsRepository: IBitcoinStatsRepository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IBitcoinStatsInteractor {
    private val _state = MutableStateFlow<BitcoinStatsState>(BitcoinStatsState.None)

    @NativeCoroutinesState
    override val state = _state.asStateFlow()

    @NativeCoroutines
    override suspend fun getStats(refresh: Boolean) {
        withContext(coroutineDispatcher) {
            _state.update { BitcoinStatsState.Loading }
            try {
                val stats = statsRepository.getStats(refresh = true)
                _state.update { BitcoinStatsState.Success(stats) }
            } catch (e: Exception) {
                println("getStats() Exception: $e")
                //errorDelegate.raiseException(e, suspend { this.refresh() })
                if (e is UDemoException) {
                    _state.update { BitcoinStatsState.Error(e.data as BitcoinStatsData?) }
                } else {
                    _state.update { BitcoinStatsState.Error(null) }
                }
            }
        }
    }
}
