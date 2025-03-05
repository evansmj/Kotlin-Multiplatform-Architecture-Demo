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
fun CashCard(dollarBalance: String) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                stringResource(R.string.cash_header),
                style = MaterialTheme.typography.titleMedium
            )
            Text(dollarBalance, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
