package com.oldgoat5.udemo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.oldgoat5.udemo.navigation.Routes
import com.oldgoat5.udemo.navigation.topLevelRoutes
import com.oldgoat5.udemo.ui.buy.BuyScreen
import com.oldgoat5.udemo.ui.portfolio.PortfolioScreen
import com.oldgoat5.udemo.ui.receive.ReceiveScreen
import com.oldgoat5.udemo.ui.theme.AppTheme

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    AppTheme() {
        val uri = "https://www.udemo.com/" // todo: app / deep link uri
        val navController = rememberNavController()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Personal account")
                    },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Outlined.Notifications,
                                contentDescription = "Notifications"
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = "Help"
                            )
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    topLevelRoutes.forEach { topLevelRoute ->
                        NavigationBarItem(
                            icon = { Icon(topLevelRoute.icon, topLevelRoute.contentDescription) },
                            label = { Text(topLevelRoute.name) },
                            selected = currentDestination?.route == topLevelRoute.route,
                            onClick = {
                                navController.navigate(topLevelRoute.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->

            NavHost(
                navController = navController,
                startDestination = Routes.Portfolio,
                Modifier.padding(innerPadding)
            ) {
                composable(
                    route = Routes.Buy,
                    deepLinks = listOf(navDeepLink { uriPattern = "$uri/buy" })
                ) {
                    BuyScreen()
                }
                composable(
                    route = Routes.Portfolio,
                    deepLinks = listOf(navDeepLink { uriPattern = "$uri/portfolio"})
                ) {
                    PortfolioScreen()
                }
                composable(
                    route = Routes.Receive,
                    deepLinks = listOf(navDeepLink { uriPattern = "$uri/receive" })
                ) {
                    ReceiveScreen()
                }
            }
        }
    }
}
