package com.oldgoat5.udemo.network.stats

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class StatsInteractor(
    private val statsRepository: IStatsRepository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IStatsInteractor {
    private val _state = MutableStateFlow<StatsState>(StatsState.None)
    override val state = _state.asStateFlow()

    override suspend fun getStats(refresh: Boolean) {
        withContext(coroutineDispatcher) {
            println("getStats()!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            _state.update { StatsState.Loading }
            try {
                val stats = statsRepository.getStats(refresh = true)
                _state.update { StatsState.Success(stats) }
                println("success!!!!!!!!!!!!")
            } catch (e: Exception) {
                println("exception $e")
                //errorDelegate.raiseException(e, suspend { this.refresh() })
                if (e is UDemoException) {
                    _state.update { StatsState.Error(e.data as StatsResponse?) }
                }
            }
        }
    }
}
