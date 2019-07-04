package com.example.viewpagerexample.viewmodel

import android.graphics.Matrix
import android.util.DisplayMetrics
import android.util.Log
import android.util.Rational
import android.util.Size
import android.view.Surface
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.lifecycle.LifecycleOwner
import com.example.viewpagerexample.databinding.FragmentCameraBinding
import com.example.viewpagerexample.fragment.CamerFragment
import kotlinx.android.synthetic.main.fragment_camera.*
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import kotlinx.android.synthetic.main.fragment_camera.view.*
import java.io.File


class CameraVM(val activity:CamerFragment, val  binding:FragmentCameraBinding) :AppCompatActivity(), LifecycleOwner{

    var viewFinder:TextureView?=null
    var captureImageButton:ImageButton?= null

    // binding camera to it's use cases to lifecycle
    fun startCamera(){
        viewFinder=binding.viewFinder
        captureImageButton=binding.captureButton
        CameraX.unbindAll()
        CameraX.bindToLifecycle(activity,buildPreviewUseCase(),buildImageCaptureUseCase())

    }

    //implementing preview use case of CameraX api

    fun buildPreviewUseCase(): Preview {
           val previewConfig = PreviewConfig.Builder().build()
           val preview = Preview(previewConfig)
           preview.setOnPreviewOutputUpdateListener { previewOutput ->
            val parent = viewFinder?.parent as ViewGroup
           parent.removeView(viewFinder)
           parent.addView(viewFinder, 0)
            viewFinder?.surfaceTexture = previewOutput.surfaceTexture

            // Compute the center of preview (TextureView)
            val centerX = viewFinder?.width?.toFloat()?.div(2)
            val centerY = viewFinder?.height?.toFloat()?.div(2)

            //    Correct preview output to account for display rotation
            val rotationDegrees = when (viewFinder?.display?.rotation) {
                Surface.ROTATION_0 -> 0
                Surface.ROTATION_90 -> 90
                Surface.ROTATION_180 -> 180
                Surface.ROTATION_270 -> 270
                else -> return@setOnPreviewOutputUpdateListener
            }

            val matrix = Matrix()
            if (centerX != null) {
                if (centerY != null) {
                    matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)
                }
            }

            // Finally, apply transformations to TextureView
           viewFinder?.setTransform(matrix)
        }

        return preview
    }
   //binding Image Capture Use Case
    fun buildImageCaptureUseCase(): ImageCapture {
        val captureConfig = ImageCaptureConfig.Builder()
                          .setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
                .build()

        val capture = ImageCapture(captureConfig)
        captureImageButton?.setOnClickListener {
            // Create temporary file
         val imageFile = File(activity.context?.externalMediaDirs?.first(),
                 "${System.currentTimeMillis()}.jpg")


            // Store captured image in the temporary file
            capture.takePicture(imageFile, object : ImageCapture.OnImageSavedListener {
                override fun onImageSaved(file: File) {
                    val msg = "Photo capture succeeded: ${file.absolutePath}"
                   Log.d("CameraXApp", msg)
                   Toast.makeText(activity.context, msg, Toast.LENGTH_SHORT).show()
                }

                override fun onError(useCaseError: ImageCapture.UseCaseError, message: String, cause: Throwable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    val msg = "Photo capture failed: $message"
                    Toast.makeText(activity.context, msg, Toast.LENGTH_SHORT).show()
                    Log.e("CameraXApp", msg)
                    cause?.printStackTrace()
                }

            })
        }

        return capture
    }



}
