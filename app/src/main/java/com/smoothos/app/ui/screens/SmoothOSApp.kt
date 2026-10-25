package com.smoothos.app.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Animation
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.smoothos.app.ui.theme.SmoothAccent
import com.smoothos.app.ui.theme.SmoothDark
import com.smoothos.app.ui.theme.SmoothTextDim

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    data object Home : Screen("home", "Home", Icons.Filled.Home)
    data object Files : Screen("files", "Files", Icons.Filled.Folder)
    data object Studio : Screen("studio", "Studio", Icons.Outlined.Animation)
    data object Apps : Screen("apps", "Apps", Icons.Filled.Apps)
    data object Settings : Screen("settings", "Settings", Icons.Filled.Settings)
}

val bottomScreens = listOf(Screen.Home, Screen.Files, Screen.Studio, Screen.Apps)

@Composable
fun SmoothOSApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        containerColor = SmoothDark,
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF0F1420),
                contentColor = SmoothAccent
            ) {
                bottomScreens.forEach { screen ->
                    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = selected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = SmoothAccent,
                            selectedTextColor = SmoothAccent,
                            unselectedIconColor = SmoothTextDim,
                            unselectedTextColor = SmoothTextDim,
                            indicatorColor = Color(0xFF1A2235)
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Files.route) { FilesScreen() }
            composable(Screen.Studio.route) { AnimationStudioScreen() }
            composable(Screen.Apps.route) { AllAppsScreen() }
            composable("recent") { RecentAppsScreen(onBack = { navController.popBackStack() }) }
        }
    }
}
