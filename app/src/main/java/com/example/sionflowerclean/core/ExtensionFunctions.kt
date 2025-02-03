package com.example.sionflowerclean.core

import android.app.Activity
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat

fun Activity.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun Activity.color(@ColorRes color: Int) = ContextCompat.getColor(this, color)

@Composable
fun Int.toPainter(): Painter {
    return painterResource(id = this)
}
