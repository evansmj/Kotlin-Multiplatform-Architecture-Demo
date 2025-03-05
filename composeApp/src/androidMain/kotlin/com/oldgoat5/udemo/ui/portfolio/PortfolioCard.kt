package com.oldgoat5.udemo.ui.portfolio

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.oldgoat5.udemo.R

@Composable
fun PortfolioCard(bitcoinPrice: String, bitcoin24HChange: String, bitcoinHoldingsBtc: String) {
    Text(
        stringResource(R.string.header_portfolio),
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier
            .padding(horizontal = 8.dp))
    Card(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(bitcoinHoldingsBtc, style = MaterialTheme.typography.bodyLarge)
            Text(bitcoinPrice, style = MaterialTheme.typography.bodyLarge)
            Text(bitcoin24HChange, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
