package com.oldgoat5.udemo

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
import androidx.compose.ui.res.stringResource
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
import com.oldgoat5.udemo.ui.vault.create.CreateVaultScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    AppTheme() {
        val uri = "udemo://"
        val navController = rememberNavController()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(R.string.toolbar_personal_account))
                    },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = stringResource(R.string.toolbar_menu_content_description)
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Outlined.Notifications,
                                contentDescription = stringResource(R.string.toolbar_notifications_content_description)
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = stringResource(R.string.toolbar_info_content_description)
                            )
                        }
                    }
                )
            },
            bottomBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                if (currentDestination?.route in topLevelRoutes.map { it.route }) {
                    NavigationBar {
                        topLevelRoutes.forEach { topLevelRoute ->
                            NavigationBarItem(
                                icon = { Icon(topLevelRoute.icon, topLevelRoute.contentDescription) },
                                label = { Text(stringResource(topLevelRoute.nameRes)) },
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
            }
        ) { innerPadding ->

            NavHost(
                navController = navController,
                startDestination = Routes.Portfolio,
                Modifier.padding(innerPadding)
            ) {
                //region home
                composable(
                    route = Routes.Buy,
                    deepLinks = listOf(navDeepLink { uriPattern = "$uri/buy" })
                ) {
                    BuyScreen()
                }
                composable(
                    route = Routes.Portfolio,
                    deepLinks = listOf(navDeepLink { uriPattern = "$uri/portfolio" })
                ) {
                    PortfolioScreen(
                        onNavigateToCreateVault = { navController.navigate(route = Routes.CreateVault) }
                    )
                }
                composable(
                    route = Routes.Receive,
                    deepLinks = listOf(navDeepLink { uriPattern = "$uri/receive" })
                ) {
                    ReceiveScreen()
                }
                //endregion

                composable(
                    route = Routes.CreateVault,
                    deepLinks = listOf(navDeepLink { uriPattern = "$uri/create-vault" })
                ) {
                    CreateVaultScreen(onClose = { navController.popBackStack() })
                }
            }
        }
    }
}
