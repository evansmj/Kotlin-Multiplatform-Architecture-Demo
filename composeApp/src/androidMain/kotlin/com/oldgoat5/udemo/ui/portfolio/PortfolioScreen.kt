package com.oldgoat5.udemo.ui.portfolio

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oldgoat5.udemo.network.stats.StatsState

@Composable
fun PortfolioScreen(
    portfolioViewModel: PortfolioViewModel = viewModel()
) {
    val statsState by portfolioViewModel.statsState.collectAsState()

    LaunchedEffect(Unit) {
        portfolioViewModel.fetchStats()
    }

    Spacer(Modifier.padding(16.dp))

    when (statsState) {
        is StatsState.Loading -> {
            CircularProgressIndicator()
        }

        is StatsState.Success -> {
            val data = (statsState as StatsState.Success).stats
            val bitcoinData = data.data["1"]
            bitcoinData?.let { Text(text = it.name) }
        }

        is StatsState.Error -> {
            val maybeData = (statsState as StatsState.Error).cachedStats
            val cachedBitcoinData = maybeData?.data?.get("1")
            cachedBitcoinData?.let { Text(text = "cached data = ${cachedBitcoinData.name}") }
                ?: Text(text = "Error and No data found")
        }

        StatsState.None -> {

        }
    }
}
