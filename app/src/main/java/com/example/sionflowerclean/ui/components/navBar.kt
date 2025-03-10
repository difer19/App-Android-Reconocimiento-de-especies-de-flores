package com.example.sionflowerclean.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.sionflowerclean.R
import com.example.sionflowerclean.core.navigation.NavigationGraph
import com.example.sionflowerclean.core.toPainter

@Composable
fun NavBar(navController: NavHostController) {
    var selectedItem by remember { mutableIntStateOf(1) }
    val items = listOf("Jardin", "Reconocimiento")
    val selectedIcons = listOf(R.drawable.flower, R.drawable.camera, R.drawable.user)
    val screens = listOf("jardin", "reconocimiento")

    Scaffold(
        bottomBar = {
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
                        onClick = {
                            selectedItem = index
                            navController.navigate(screens[index]) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Box(modifier = Modifier.padding(paddingValues)) {
                NavigationGraph(navController)
            }
        }
    }

}

