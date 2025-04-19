package com.example.pupilmeshtask.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pupilmeshtask.UserPreferenceManager
import com.example.pupilmeshtask.presentation.screens.FaceDetectionScreenUI
import com.example.pupilmeshtask.presentation.screens.HomeScreenUI
import com.example.pupilmeshtask.presentation.screens.LoginScreenUI
import com.example.pupilmeshtask.presentation.screens.MangaDetailsScreenUI

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var startDestination by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val userPreferences = UserPreferenceManager


//    LaunchedEffect(Unit) {
//        val isLoggedIn = userPreferences.
//        startDestination =
//            if (isLoggedIn) HomeScreenRoute::class.qualifiedName else LoginScreenRoute::class.qualifiedName
//    }

//    if (startDestination == null) {
//
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            CircularProgressIndicator()
//        }
//    } else {
        val currentDestination by navController.currentBackStackEntryAsState()
        val bottomBarScreens = listOf(
            HomeScreenRoute::class.qualifiedName,
            MangaDetailScreenRoute::class.qualifiedName,
            FaceDetectionScreenRoute::class.qualifiedName,

            )
        val currentRoute = currentDestination?.destination?.route
        val bottomBarVisibility = currentRoute in bottomBarScreens

        Scaffold(
            bottomBar = {
                if (bottomBarVisibility) {
                    BottomNavigationBar(navController)
                }
            }
        ) {
            Box(modifier = Modifier.padding(it)) {

                NavHost(navController = navController, startDestination = LoginScreenRoute) {
                    composable<LoginScreenRoute> {
                        LoginScreenUI(navController)
                    }
                    composable<HomeScreenRoute> {
                        HomeScreenUI(navController)
                    }
                    composable<MangaDetailScreenRoute> {
                        MangaDetailsScreenUI(navController)
                    }
                    composable<FaceDetectionScreenRoute> {
                        FaceDetectionScreenUI(navController)
                    }

                }
            }

        }


}