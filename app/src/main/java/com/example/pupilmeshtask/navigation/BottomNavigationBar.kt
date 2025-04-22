package com.example.pupilmeshtask.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Manga", HomeScreenRoute::class.qualifiedName!!, Icons.Default.MenuBook),
        BottomNavItem("Face", FaceDetectionScreenRoute::class.qualifiedName!!, Icons.Default.Face)
    )

    NavigationBar{
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label ) },
                label = { Text(item.label, ) },
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
