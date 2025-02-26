package com.oldgoat5.udemo.ui.portfolio

import com.oldgoat5.udemo.network.stats.IStatsInteractor
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PortfolioViewModel : ViewModel(), KoinComponent {
    private val statsInteractor: IStatsInteractor by inject()

    @NativeCoroutinesState
    val statsState = statsInteractor.state

    @NativeCoroutines
    suspend fun fetchStats() {
        statsInteractor.getStats(true)
    }
}
