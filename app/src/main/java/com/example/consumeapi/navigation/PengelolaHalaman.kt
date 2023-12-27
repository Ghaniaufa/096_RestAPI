package com.example.consumeapi.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.consumeapi.ui.kontak.screen.DestinasiEntry
import com.example.consumeapi.ui.kontak.screen.EntryKontakScreen
import com.example.consumeapi.ui.screens.DestinasiHome
import com.example.consumeapi.ui.screens.HomeScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {

  NavHost(
    navController = navController,
    startDestination = DestinasiHome.route,
    modifier = Modifier
  ) {

    composable(DestinasiHome.route) {
      HomeScreen(navigatetoItemEntry = {
        navController.navigate(
          (DestinasiEntry.route)
        )

      }, onDetailClick = {})
    }
    composable(DestinasiEntry.route){
      EntryKontakScreen(navigateBack = {
        navController.navigate(
          DestinasiHome.route
        ){
          popUpTo(DestinasiHome.route){
            inclusive = true
          }
        }
      })
    }

  }
}

