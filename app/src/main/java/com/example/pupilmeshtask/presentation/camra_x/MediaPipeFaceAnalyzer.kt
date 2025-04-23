package com.example.pupilmeshtask.presentation.camra_x


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.RectF
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mediapipe.tasks.vision.core.ImageProcessingOptions
import android.graphics.Rect
import android.graphics.YuvImage
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.tasks.vision.facedetector.FaceDetector
import com.google.mediapipe.tasks.vision.facedetector.FaceDetectorResult
import java.io.ByteArrayOutputStream

class MediaPipeFaceAnalyzer(
    private val faceDetector: FaceDetector,
    private val onFaceInsideBox: (Boolean) -> Unit
) : ImageAnalysis.Analyzer {

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val bitmap = imageProxy.toBitmap() ?: return imageProxy.close()
        val mpImage = BitmapImageBuilder(bitmap).build()

        val options = ImageProcessingOptions.builder()
            .setRotationDegrees(imageProxy.imageInfo.rotationDegrees)
            .build()

        val result = faceDetector.detect(mpImage, options)
        val faceBox = result.detections().firstOrNull()?.boundingBox()

        if (faceBox != null) {
            val isInside = faceBox.isInsideCenterBox(bitmap.width, bitmap.height)
            onFaceInsideBox(isInside)
        } else {
            onFaceInsideBox(false)
        }

        imageProxy.close()
    }
}

fun RectF.isInsideCenterBox(width: Int, height: Int): Boolean {
    val centerBox = RectF(
        width * 0.25f,
        height * 0.25f,
        width * 0.75f,
        height * 0.75f
    )
    return centerBox.contains(this)
}

