package com.oldgoat5.udemo.ui.portfolio

import com.oldgoat5.udemo.network.stats.BitcoinStatsData
import com.oldgoat5.udemo.network.stats.BitcoinStatsState
import com.oldgoat5.udemo.network.stats.IBitcoinStatsInteractor
import com.oldgoat5.udemo.network.user.IUserDataInteractor
import com.oldgoat5.udemo.network.user.UserDataResponse
import com.oldgoat5.udemo.network.user.UserDataState
import com.oldgoat5.udemo.ui.portfolio.PortfolioItem.CashCardData
import com.oldgoat5.udemo.ui.portfolio.PortfolioItem.PortfolioCardData
import com.oldgoat5.udemo.ui.portfolio.PortfolioItem.VaultCardData
import com.oldgoat5.udemo.util.calculateDollarBalance
import com.oldgoat5.udemo.util.formatDollars
import com.oldgoat5.udemo.util.satsToBtc
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.math.round

class PortfolioViewModel : ViewModel(), KoinComponent {
    private val bitcoinStatsInteractor: IBitcoinStatsInteractor by inject()
    private val userDataInteractor: IUserDataInteractor by inject()

    @NativeCoroutinesState
    val isLoading: StateFlow<Boolean> = bitcoinStatsInteractor.state
        .map { it is BitcoinStatsState.Loading }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    @NativeCoroutinesState
    val portfolioItemsList: StateFlow<List<PortfolioItem>> =
        combine(
            bitcoinStatsInteractor.state.filterIsInstance<BitcoinStatsState.Success>(),
            userDataInteractor.state.filterIsInstance<UserDataState.Success>()
        ) { bitcoinStatsState, userDataState ->
            val returnList = getItemsList(bitcoinStatsState.stats, userDataState.userData)
            returnList
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    @NativeCoroutinesState
    val error: StateFlow<String?> =
        combine(
            bitcoinStatsInteractor.state,
            userDataInteractor.state
        ) { bitcoinState, userState ->
            listOfNotNull(
                if (bitcoinState is BitcoinStatsState.Error) "bitcoinStatsError" else null,
                if (userState is UserDataState.Error) "userDataError" else null
            ).joinToString(" + ").ifEmpty { null }
        }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    @NativeCoroutines
    suspend fun fetchBitcoinStats() {
        bitcoinStatsInteractor.getStats(true)
    }

    @NativeCoroutines
    suspend fun fetchUserData() {
        userDataInteractor.getUserData()
    }

    private fun getItemsList(
        bitcoinStats: BitcoinStatsData,
        userData: UserDataResponse
    ): List<PortfolioItem> {
        val itemsList = mutableListOf<PortfolioItem>()

        itemsList.add(
            PortfolioCardData(
                formatDollars(round(bitcoinStats.quote.usd.price * 100) / 100),
                (round(bitcoinStats.quote.usd.percentChange24h * 100) / 100).toString() + "%",
                satsToBtc(userData.bitcoinHoldingsSats).toString() + " BTC"
            )
        )

        val dollarBalance = round(
            calculateDollarBalance(
                bitcoinStats.quote.usd.price,
                userData.bitcoinHoldingsSats
            ) * 100
        ) / 100

        itemsList.add(CashCardData(formatDollars(dollarBalance)))

        itemsList.add(VaultCardData())

        return itemsList
    }
}
