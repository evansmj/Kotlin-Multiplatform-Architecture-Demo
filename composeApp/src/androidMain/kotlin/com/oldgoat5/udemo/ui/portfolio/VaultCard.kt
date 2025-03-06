package com.oldgoat5.udemo.ui.portfolio

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.oldgoat5.udemo.R

@Composable
fun VaultCard(
    onNavigateToCreateVault: () -> Unit
) {
    Button(onClick = onNavigateToCreateVault) {
        Text(stringResource(R.string.vault_button), style = MaterialTheme.typography.labelMedium)
    }
}
