package com.track.unit

import com.track.unit.logs.History
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.track.unit.compnents.Form
import com.track.unit.data.UnitRepository
import com.track.unit.main.MainScreen

@Composable
fun NavHostContainer(
    navHostController: NavHostController,
    innerpadding: PaddingValues,
    unitRepository: UnitRepository
) {
    NavHost(
        navController = navHostController,
        startDestination = "MainScreen",
        modifier = Modifier.padding(innerpadding)
    ) {
        composable("MainScreen") {
            MainScreen(unitRepository = unitRepository)
        }
        composable("history") {
            History(repository = unitRepository) { Form(Repository = unitRepository) }
        }
    }
}


@Composable
fun BottomNavBar(navController: NavHostController) {

    val items = listOf(
        BottomItem("Home", "MainScreen", Icons.Filled.Home, Icons.Outlined.Home),
        BottomItem("History", "history", Icons.AutoMirrored.Filled.List, Icons.AutoMirrored.Outlined.List)
    )

    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo("MainScreen") { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        if (currentRoute == item.route) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) }
            )
        }
    }
}

data class BottomItem(
    val label: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
