package com.oldgoat5.udemo.di

import com.oldgoat5.udemo.network.stats.BitcoinStatsInteractor
import com.oldgoat5.udemo.network.stats.BitcoinStatsRepository
import com.oldgoat5.udemo.network.stats.IBitcoinStatsInteractor
import com.oldgoat5.udemo.network.stats.IBitcoinStatsRepository
import com.oldgoat5.udemo.network.user.IUserDataInteractor
import com.oldgoat5.udemo.network.user.IUserDataRepository
import com.oldgoat5.udemo.network.user.UserDataInteractor
import com.oldgoat5.udemo.network.user.UserDataRepository
import org.koin.dsl.module

val domainModule = module {
    single<IBitcoinStatsRepository> { BitcoinStatsRepository(get()) }
    single<IBitcoinStatsInteractor> { BitcoinStatsInteractor(get()) }

    single<IUserDataRepository> { UserDataRepository(get()) }
    single<IUserDataInteractor> { UserDataInteractor(get()) }
}
