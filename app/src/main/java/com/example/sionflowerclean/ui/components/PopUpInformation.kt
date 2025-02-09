package com.example.sionflowerclean.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.sionflowerclean.data.model.PlantIdentificationResult

@Composable
fun PopupSample(result : PlantIdentificationResult, onDismiss: () -> Unit) {
    Popup(alignment = Alignment.Center, onDismissRequest = { onDismiss() }) {
        Box(
            Modifier
                .fillMaxSize(0.8f)
                .background(Color.White, RoundedCornerShape(16.dp))
                .clickable { }
        ) {
            Text(
                text = result.scientificName,
                modifier = Modifier.align(Alignment.Center),
                color = Color.Black
            )
        }
    }
}