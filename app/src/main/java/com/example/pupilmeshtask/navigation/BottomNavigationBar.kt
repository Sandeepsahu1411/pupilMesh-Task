package com.example.pupilmeshtask.navigation

import android.R.attr.label
import android.R.attr.onClick
import android.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector


import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Manga", HomeScreenRoute::class.qualifiedName!!, Icons.Default.MenuBook),
        BottomNavItem("Face", FaceDetectionScreenRoute::class.qualifiedName!!, Icons.Default.Face)
    )

    NavigationBar(
        containerColor = androidx.compose.ui.graphics.Color(0xff282828),
    ) {

        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = androidx.compose.ui.graphics.Color.DarkGray
                ),
                icon = { Icon(item.icon, contentDescription = item.label ,tint = androidx.compose.ui.graphics.Color.White) },
                label = { Text(item.label,color = androidx.compose.ui.graphics.Color.White ) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(HomeScreenRoute) { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}

data class BottomNavItem(val label: String, val route: String, val icon: ImageVector)
