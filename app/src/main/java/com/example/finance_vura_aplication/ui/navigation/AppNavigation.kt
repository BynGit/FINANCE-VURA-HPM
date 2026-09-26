package com.example.finance_vura_aplication.ui.navigation


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.finance_vura_aplication.ui.views.Accionescreen
import com.example.finance_vura_aplication.ui.views.FotosScreen
import com.example.finance_vura_aplication.ui.views.PerfilScreen
import com.example.finance_vura_aplication.ui.views.VideoScreen
import com.example.finance_vura_aplication.ui.views.WebScreen
import androidx.navigation.compose.NavHost
import androidx.compose.ui.Modifier


@Composable
fun AppNavigation( navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "Perfil",
        modifier = Modifier
            .fillMaxHeight()

    ) {
        composable("Perfil") { PerfilScreen() }
        composable("Fotos") { FotosScreen("Pantalla de Fotos") }
        composable("Video") { VideoScreen("Pantalla de Video") }
        composable("Web") { WebScreen("Pantalla de Web") }
        composable("Acciones") { Accionescreen("Pantalla de Acciones") }
    }

}




