package com.example.sionflowerclean.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sionflowerclean.ui.view.JardinScreen
import com.example.sionflowerclean.ui.view.PerfilScreen
import com.example.sionflowerclean.ui.view.ReconocimientoScreen


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "reconocimiento") {
        composable("jardin") { JardinScreen() }
        composable("reconocimiento") { ReconocimientoScreen() }
        composable("perfil") { PerfilScreen() }
    }
}
