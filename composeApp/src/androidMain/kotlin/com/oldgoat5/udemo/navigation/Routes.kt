package com.oldgoat5.udemo.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.oldgoat5.udemo.R

data class TopLevelRoute<T : Any>(@StringRes val nameRes: Int, val route: T, val icon: ImageVector, val contentDescription: String)

val topLevelRoutes = listOf(
    TopLevelRoute(R.string.navbar_buy, Routes.Buy, Icons.Default.ShoppingCart, "Buy"),
    TopLevelRoute(R.string.navbar_portfolio, Routes.Portfolio, Icons.Default.Home, "Portfolio"),
    TopLevelRoute(R.string.navbar_receive, Routes.Receive, Icons.Default.Add, "Receive")
)

object Routes {
    const val Portfolio = "portfolio"
    const val Buy = "buy"
    const val Receive = "receive"
}
