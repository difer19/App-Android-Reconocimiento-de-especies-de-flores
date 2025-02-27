package com.example.sionflowerclean.ui.view

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sionflowerclean.R
import com.example.sionflowerclean.core.createImageFile
import com.example.sionflowerclean.core.getResourceUri
import com.example.sionflowerclean.core.rememberImagePainterFromUri
import com.example.sionflowerclean.core.toPainter
import com.example.sionflowerclean.ui.components.PopupSample
import com.example.sionflowerclean.ui.viewmodel.ReconocimientoViewModel
import java.util.Objects
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import com.example.sionflowerclean.core.toMultipart


@Preview
@Composable
fun ReconocimientoScreen() {
    val reconocimientoViewModel: ReconocimientoViewModel = viewModel()
    val flowerData by reconocimientoViewModel.plantIdentificationResult.observeAsState()
    val informationState by reconocimientoViewModel.informationState.observeAsState()
    val progressBarState by reconocimientoViewModel.progressBarState.observeAsState()
    val imageState = MutableLiveData<Boolean>()

    val defaultImage = LocalContext.current.getResourceUri(R.drawable.flower_preview)
    val context = LocalContext.current
    val file = context.createImageFile()
    val uriCamara = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        "com.example.sionflowerclean" + ".provider", file
    )

    var photoUri: Uri? by remember { mutableStateOf(null) }
    var capturedImageUri by remember { mutableStateOf(Uri.EMPTY) }


    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            capturedImageUri = uriCamara
            photoUri = null
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uriCamara)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }


    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        photoUri = uri
        capturedImageUri = null
    }


    var painter = LocalContext.current.rememberImagePainterFromUri(defaultImage)
    imageState.value = false
    if (photoUri !== null) {
        painter = LocalContext.current.rememberImagePainterFromUri(photoUri)
        imageState.value = true
    }else if (capturedImageUri !== Uri.EMPTY){
        painter = LocalContext.current.rememberImagePainterFromUri(capturedImageUri)
        imageState.value = true
    }

    if (informationState == true) {
        flowerData?.let { PopupSample(it, { reconocimientoViewModel.closePopUp() }) }
    }

    Column (
        modifier = Modifier.fillMaxSize().padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "Identificacion de Flores",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
        )
        Spacer(modifier = Modifier.height(15.dp))

        Image(
            painter = painter,
            contentDescription = "Imagen desde URI",
            modifier = Modifier.fillMaxWidth(0.8f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(20.dp))
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "📷 Toma una foto de la flor o súbela desde la galería.",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    launcher.launch(
                        PickVisualMediaRequest(
                            mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }
            ) {
                Icon(
                    painter = R.drawable.gallery.toPainter(),
                    contentDescription = "Icono de galería",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Seleccionar Foto")
            }

            Spacer(modifier = Modifier.width(12.dp))
            Button(
                onClick = {
                    val permissionCheckResult =
                        ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                        cameraLauncher.launch(uriCamara)
                    } else {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                }
            ) {
                Icon(
                    painter = R.drawable.photo_camera.toPainter(),
                    contentDescription = "Icono de camara",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Tomar Foto")
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {
                if (imageState.value == true) {
                    reconocimientoViewModel.changeStateProgressBar(true)
                    if (photoUri !== defaultImage) {
                        photoUri!!.toMultipart(context)
                            ?.let { reconocimientoViewModel.recognizeFlower(it) }
                    }else if (capturedImageUri !== Uri.EMPTY){
                        capturedImageUri!!.toMultipart(context)
                            ?.let { reconocimientoViewModel.recognizeFlower(it) }
                    }
                }else{
                    Toast.makeText(context, "No se ha seleccionado una imagen", Toast.LENGTH_SHORT).show()
                    reconocimientoViewModel.changeStateProgressBar(false)
                }
            }
        ) {
            Icon(
                painter = R.drawable.search.toPainter(),
                contentDescription = "Identificar",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Identificar")
        }

        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = R.drawable.plantnet.toPainter(),
            contentDescription = "Imagen desde URI",
            modifier = Modifier.fillMaxWidth(0.5f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(45.dp))
        )

        Spacer(modifier = Modifier.height(40.dp))

        if (progressBarState == true) {
            CircularProgressIndicator()
        }

    }
}
