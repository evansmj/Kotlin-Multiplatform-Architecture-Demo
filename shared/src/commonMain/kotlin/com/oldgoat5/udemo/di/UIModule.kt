package com.oldgoat5.udemo.di

import com.oldgoat5.udemo.network.stats.IStatsInteractor
import com.oldgoat5.udemo.network.stats.IStatsRepository
import com.oldgoat5.udemo.network.stats.StatsInteractor
import com.oldgoat5.udemo.network.stats.StatsRepository
import com.oldgoat5.udemo.ui.portfolio.PortfolioViewModel
import org.koin.dsl.module

val uiModule = module {
    single<IStatsRepository> { StatsRepository(get()) }
    single<IStatsInteractor> { StatsInteractor(get()) }

    factory { PortfolioViewModel(get()) }
}
