package com.example.pupilmeshtask.presentation.components

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun LoadingBar(
    size: Dp = 40.dp,
    color: Color = Color.Transparent
) {
    Box(
        modifier = Modifier
            .zIndex(1f)
            .fillMaxSize()
            .background(color), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(size),
            color = Color.White
        )
    }

}
@Composable
fun CameraPermission(
    onPermissionGranted: @Composable () -> Unit
) {
    val context = LocalContext.current
    val permission = android.Manifest.permission.CAMERA
    val isGranted = remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted.value = it }
    )
    LaunchedEffect(Unit) {
        if (context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
            isGranted.value = true
        } else {
            launcher.launch(permission)
        }
    }

    if (isGranted.value) {
        onPermissionGranted()
    }else{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Camera permission is required to detect face.", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { launcher.launch(permission) }) {
                Text("Grant Permission")
            }
        }
    }

}