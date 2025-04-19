package com.example.pupilmeshtask.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pupilmeshtask.navigation.HomeScreenRoute
import com.example.pupilmeshtask.navigation.LoginScreenRoute
import com.example.pupilmeshtask.presentation.viewmodel.NewViewModel


@Composable
fun LoginScreenUI(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val viewModel: NewViewModel = hiltViewModel()
    val loginResult by viewModel.loginResult.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.checkLoggedInUser { navController.navigate(HomeScreenRoute) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.login(email, password) }, modifier = Modifier.fillMaxWidth()) {
            Text("Login / Register")
        }
    }

    loginResult?.let {
        if (it) {
            LaunchedEffect(Unit) {
                navController.navigate(HomeScreenRoute) {
                    popUpTo(LoginScreenRoute) { inclusive = true }
                }
            }
        } else {
            Toast.makeText(LocalContext.current, "Invalid password", Toast.LENGTH_SHORT).show()
        }
    }
}
