package com.example.pupilmeshtask.presentation.screens

import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview

import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import java.util.concurrent.Executor
import javax.annotation.Nonnull

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun FaceDetectionScreenUI() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val lens = CameraSelector.LENS_FACING_FRONT
    val preview = Preview.Builder().build()
    val PreviewView: PreviewView = remember { PreviewView(context) }

    val cameraSelector = CameraSelector.Builder().requireLensFacing(lens).build()

    val imageCapture = remember { ImageCapture.Builder().build() }

    LaunchedEffect(Unit) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner, cameraSelector, preview, imageCapture
        )

        preview.surfaceProvider = PreviewView.surfaceProvider
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {

        AndroidView(factory = {
            PreviewView
        }, modifier = Modifier.fillMaxSize())

        Button(onClick = { takePhoto(imageCapture, context) }) {
            Text("Take Photo")
        }

    }

}

private suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine {
    ProcessCameraProvider.getInstance(this).also { cameraProvider ->
        cameraProvider.addListener({
            it.resume(cameraProvider.get())
        }, ContextCompat.getMainExecutor(this))
    }
}

private fun takePhoto(imageCapture: ImageCapture, context: Context) {
    val name = "camera-x${System.currentTimeMillis()}.jpg"

    val contentValue = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")

    }
    val outPutOption = ImageCapture.OutputFileOptions.Builder(
        context.contentResolver,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        contentValue
    ).build()
    imageCapture.takePicture(
        outPutOption,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                Toast.makeText(context, "Image Saved", Toast.LENGTH_LONG).show()
            }

            override fun onError(exception: ImageCaptureException) {
                Toast.makeText(context, "Image not Saved", Toast.LENGTH_LONG).show()
            }
        }
    )

}

