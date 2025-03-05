package com.oldgoat5.udemo.ui.portfolio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PortfolioScreen(
    portfolioViewModel: PortfolioViewModel = viewModel()
) {
    val isLoading by portfolioViewModel.isLoading.collectAsStateWithLifecycle()
    val error by portfolioViewModel.error.collectAsStateWithLifecycle()
    val portfolioItemsList by portfolioViewModel.portfolioItemsList.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        portfolioViewModel.fetchBitcoinStats()
        portfolioViewModel.fetchUserData()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        if (!error.isNullOrEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Error message: $error", color = Color.Red)
            }
        }

        if (portfolioItemsList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(portfolioItemsList) { item ->
                    when (item) {
                        is PortfolioItem.PortfolioCardData -> PortfolioCard(
                            item.bitcoinPrice,
                            item.bitcoin24HChange,
                            item.bitcoinHoldingsBtc,
                        )

                        is PortfolioItem.CashCardData -> CashCard(item.dollarBalance)
                        is PortfolioItem.VaultCardData -> VaultCard()
                    }
                }
            }
        }
    }
}
