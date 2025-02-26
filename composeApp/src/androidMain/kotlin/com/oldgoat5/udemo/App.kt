package com.oldgoat5.udemo

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
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

@Composable
fun App() {
    MaterialTheme {
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
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    topLevelRoutes.forEach { topLevelRoute ->
                        BottomNavigationItem(
                            icon = { Icon(topLevelRoute.icon, topLevelRoute.contentDescription) },
                            label = { Text(topLevelRoute.name) },
                            selected = currentDestination?.route == topLevelRoute.route.toString(),
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
                composable<Routes.Buy>(
                    deepLinks = listOf(navDeepLink<Routes.Buy>(basePath = "$uri/buy"))
                ) {
                    BuyScreen()
                }
                composable<Routes.Portfolio>(
                    deepLinks = listOf(navDeepLink<Routes.Portfolio>(basePath = "$uri/portfolio"))
                ) {
                    PortfolioScreen()
                }
                composable<Routes.Receive>(
                    deepLinks = listOf(navDeepLink<Routes.Receive>(basePath = "$uri/receive"))
                ) {
                    ReceiveScreen()
                }
            }
        }
    }
}
