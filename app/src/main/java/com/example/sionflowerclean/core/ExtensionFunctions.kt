package com.example.sionflowerclean.core

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date

fun Activity.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun Activity.color(@ColorRes color: Int) = ContextCompat.getColor(this, color)

@Composable
fun Int.toPainter(): Painter {
    return painterResource(id = this)
}

fun Context.getResourceUri(@DrawableRes resId: Int): Uri {
    return Uri.parse("android.resource://$packageName/$resId")
}

@Composable
fun Context.rememberImagePainterFromUri(uri: Uri?): Painter {
    return rememberAsyncImagePainter(
        ImageRequest.Builder(this)
            .data(uri)
            .build()
    )
}

fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
    return image
}

fun Uri.toMultipart(context: Context, paramName: String = "file"): MultipartBody.Part? {
    val contentResolver = context.contentResolver
    val fileType = contentResolver.getType(this) ?: "image/jpeg"
    val inputStream = contentResolver.openInputStream(this) ?: return null
    val file = File(context.cacheDir, "temp_file_${System.currentTimeMillis()}")

    file.outputStream().use { outputStream ->
        inputStream.copyTo(outputStream)
    }

    val mediaType = fileType.toMediaTypeOrNull() ?: "image/jpeg".toMediaTypeOrNull()
    val requestBody = file.asRequestBody(mediaType)
    return MultipartBody.Part.createFormData(paramName, file.name, requestBody)
}


