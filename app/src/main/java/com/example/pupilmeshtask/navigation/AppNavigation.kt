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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pupilmeshtask.data_layer.room_db.database.UserPreferenceManager
import com.example.pupilmeshtask.presentation.components.CameraPermission
import com.example.pupilmeshtask.presentation.screens.FaceDetectionScreenUI
import com.example.pupilmeshtask.presentation.screens.HomeScreenUI
import com.example.pupilmeshtask.presentation.screens.LoginScreenUI
import com.example.pupilmeshtask.presentation.screens.MangaDetailsScreenUI
import com.example.pupilmeshtask.presentation.viewmodel.AppViewModel
import kotlinx.coroutines.flow.firstOrNull

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val userPref = remember { UserPreferenceManager(context) }
    var startDestination by remember { mutableStateOf<String?>(null) }
    val viewModel : AppViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        val email = userPref.userEmail.firstOrNull()
        startDestination = (if (email != null) {
            HomeScreenRoute::class.qualifiedName
        } else {
            LoginScreenRoute::class.qualifiedName
        }).toString()


    }

    if (startDestination == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val bottomBarScreens = listOf(
        HomeScreenRoute::class.qualifiedName,
        MangaDetailScreenRoute::class.qualifiedName,
        FaceDetectionScreenRoute::class.qualifiedName,
    )
    val showBottomBar = currentRoute in bottomBarScreens

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController)
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            NavHost(navController = navController, startDestination = startDestination!!) {
                composable<LoginScreenRoute> {
                    LoginScreenUI(navController,viewModel)
                }
                composable<HomeScreenRoute> {
                    HomeScreenUI(navController,viewModel)
                }
                composable<MangaDetailScreenRoute> {
                    val data = it.arguments?.getString("id")

                    MangaDetailsScreenUI(navController, id = data.toString(),viewModel)
                }
                composable<FaceDetectionScreenRoute> {
                    CameraPermission{
                    FaceDetectionScreenUI()

                    }


                }
            }
        }
    }

}
