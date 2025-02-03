package com.example.sionflowerclean.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.sionflowerclean.R
import com.example.sionflowerclean.core.toPainter


@Composable
fun returnNavBar() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Jardin", "Reconocimiento", "Perfil")
    val selectedIcons = listOf(R.drawable.flower, R.drawable.camera, R.drawable.user)


    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = selectedIcons[index].toPainter(),
                        contentDescription = item
                    )
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

