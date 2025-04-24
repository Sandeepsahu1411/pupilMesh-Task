package com.example.pupilmeshtask.presentation.camra_x

import android.content.Context
import com.google.mediapipe.framework.image.MPImage
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.facedetector.FaceDetector
import com.google.mediapipe.tasks.vision.facedetector.FaceDetectorResult

object MediaPipeFaceDetector {

    fun buildDetector(
        context: Context,
        onResult: (FaceDetectorResult, MPImage, Long) -> Unit
    ): FaceDetector {
        val baseOptions = BaseOptions.builder()
            .setModelAssetPath("face_detection_short_range.tflite")
            .build()

        val options = FaceDetector.FaceDetectorOptions.builder()
            .setBaseOptions(baseOptions)
            .setRunningMode(RunningMode.IMAGE)
//            .setResultListener() { result, image ->
//                onResult(result , image, System.currentTimeMillis())
//            }
            .build()

        return FaceDetector.createFromOptions(context, options)
    }
}


