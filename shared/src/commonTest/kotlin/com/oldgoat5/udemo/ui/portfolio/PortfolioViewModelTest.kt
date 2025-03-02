package com.oldgoat5.udemo.ui.portfolio

import app.cash.turbine.test
import com.oldgoat5.udemo.network.stats.BitcoinStatsTestDataFactory
import com.oldgoat5.udemo.network.stats.FakeBitcoinStatsInteractor
import com.oldgoat5.udemo.network.stats.IBitcoinStatsInteractor
import com.oldgoat5.udemo.network.user.FakeUserDataInteractor
import com.oldgoat5.udemo.network.user.IUserDataInteractor
import com.oldgoat5.udemo.network.user.UserTestDataFactory
import com.oldgoat5.udemo.util.calculateDollarBalance
import com.oldgoat5.udemo.util.formatDollars
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.mp.KoinPlatform.getKoin
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * CI should run 'android (local)' and 'iosSimulatorArm64' test, so that the separate 'actual' functions
 * such as in FormatUtil.kt get tested with this test.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class PortfolioViewModelTest {
    private lateinit var fakeBitcoinStatsInteractor: FakeBitcoinStatsInteractor
    private lateinit var fakeUserDataInteractor: FakeUserDataInteractor
    private lateinit var viewModel: PortfolioViewModel

    private val testDispatcher = StandardTestDispatcher()

    private val testModule = module {
        single<IBitcoinStatsInteractor> { FakeBitcoinStatsInteractor() }
        single<IUserDataInteractor> { FakeUserDataInteractor() }
    }

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        stopKoin()

        startKoin {
            modules(testModule)
        }

        fakeBitcoinStatsInteractor = getKoin().get<IBitcoinStatsInteractor>() as FakeBitcoinStatsInteractor
        fakeUserDataInteractor = getKoin().get<IUserDataInteractor>() as FakeUserDataInteractor

        viewModel = PortfolioViewModel()
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchBitcoinStats success but user data error`() = runTest {
        fakeBitcoinStatsInteractor.emitSuccess(
            BitcoinStatsTestDataFactory.createBitcoinStatsData(
                quote = BitcoinStatsTestDataFactory.createQuote(
                    usd = BitcoinStatsTestDataFactory.createUSDQuote(price = 50000.0, percentChange24h = 2.5)
                )
            )
        )

        fakeUserDataInteractor.emitError()

        viewModel.fetchBitcoinStats()
        viewModel.fetchUserData()

        viewModel.error.test {
            val initialState = awaitItem()
            val secondState = awaitItem()
            assertEquals("userDataError", secondState)
        }

        viewModel.isLoading.test {
            val first = awaitItem()
            assertEquals(false, first)
        }
    }

    @Test
    fun `fetchBitcoinStats error but user data success`() = runTest {
        fakeBitcoinStatsInteractor.emitError()
        fakeUserDataInteractor.emitSuccess(UserTestDataFactory.createUserDataResponse())

        viewModel.fetchBitcoinStats()
        viewModel.fetchUserData()

        viewModel.error.test {
            val initialState = awaitItem()
            val secondState = awaitItem()
            assertEquals("bitcoinStatsError", secondState)
        }
    }

    @Test
    fun `fetchBitcoinStats error and user data error`() = runTest {
        fakeBitcoinStatsInteractor.emitError()
        fakeUserDataInteractor.emitError()

        viewModel.fetchBitcoinStats()
        viewModel.fetchUserData()

        viewModel.error.test {
            val initialState = awaitItem()
            val secondState = awaitItem()
            assertEquals("bitcoinStatsError + userDataError", secondState)
        }
    }

    @Test
    fun `portfolioItemsList emits correct values`() = runTest {
        val bitcoinStats = BitcoinStatsTestDataFactory.createBitcoinStatsData(
            quote = BitcoinStatsTestDataFactory.createQuote(
                usd = BitcoinStatsTestDataFactory.createUSDQuote(price = 50000.0, percentChange24h = 2.5)
            )
        )
        val userData = UserTestDataFactory.createUserDataResponse(bitcoinHoldingsSats = 100_000_000)

        fakeBitcoinStatsInteractor.emitSuccess(bitcoinStats)
        fakeUserDataInteractor.emitSuccess(userData)

        val expectedPortfolioItems = listOf(
            PortfolioItem.PortfolioCardData(
                bitcoinPrice = "$50,000.00",
                bitcoin24HChange = "2.5%",
                bitcoinHoldingsBtc = "1.0 BTC"
            ),
            PortfolioItem.CashCardData(formatDollars(calculateDollarBalance(50000.0, 100_000_000))),
            PortfolioItem.VaultCardData()
        )

        viewModel.portfolioItemsList.test {
            awaitItem()
            val items = awaitItem()
            assertEquals(expectedPortfolioItems, items)
            cancelAndIgnoreRemainingEvents()
        }
    }

}
