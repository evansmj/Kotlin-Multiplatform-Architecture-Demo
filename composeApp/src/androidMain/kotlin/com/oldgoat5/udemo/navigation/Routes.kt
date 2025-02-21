package com.oldgoat5.udemo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector, val contentDescription: String)

val topLevelRoutes = listOf(
    TopLevelRoute("Portfolio", Routes.Portfolio, Icons.Default.Home, "Portfolio"),
    TopLevelRoute("Buy", Routes.Buy, Icons.Default.ShoppingCart, "Buy"),
    TopLevelRoute("Receive", Routes.Receive, Icons.Default.Add, "Receive")
)

class Routes {
    @Serializable
    object Portfolio

    @Serializable
    object Buy

    @Serializable
    object Receive
}
