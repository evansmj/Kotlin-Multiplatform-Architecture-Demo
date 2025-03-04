package com.oldgoat5.udemo.di

import com.oldgoat5.udemo.network.stats.BitcoinStatsData
import com.oldgoat5.udemo.network.stats.IBitcoinStatsRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin

class KoinHelper : KoinComponent {
    fun initKoin() {
        startKoin {
            modules(appModule)
        }
    }

    fun getBitcoinStatsRepository() : IBitcoinStatsRepository = get()

    @NativeCoroutines
    suspend fun fetchBitcoinStats(refresh: Boolean): BitcoinStatsData {
        return getBitcoinStatsRepository().getStats(true)
    }
}
