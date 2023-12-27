package com.example.consumeapi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.consumeapi.ui.screens.DestinasiHome

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {

  NavHost(
    navController = navController,
    startDestination = DestinasiHome.route,
    modifier = Modifier,
  ){

  }

}

