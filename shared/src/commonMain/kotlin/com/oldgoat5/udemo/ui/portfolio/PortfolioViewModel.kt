package com.oldgoat5.udemo.ui.portfolio

import com.oldgoat5.udemo.network.stats.IStatsInteractor
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch

class PortfolioViewModel(
    statsInteractor: IStatsInteractor
) : ViewModel() {
    val statsState = statsInteractor.state

    init {
        viewModelScope.launch {
            statsInteractor.getStats(true)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}
