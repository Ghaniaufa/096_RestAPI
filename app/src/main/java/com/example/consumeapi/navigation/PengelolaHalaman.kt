package com.example.consumeapi.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.consumeapi.R
import com.example.consumeapi.model.Kontak
import com.example.consumeapi.ui.PenyediaViewModel
import com.example.consumeapi.ui.TopAppBarKontak
import com.example.consumeapi.ui.home.viewmodel.HomeViewModel
import com.example.consumeapi.ui.home.viewmodel.KontakUIState
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
  ){

    composable(DestinasiHome.route){
      HomeScreen(navigateToItemEntry = {
        navController.navigate(
          DestinasiEntry.route)
      },
        onDetailClick = {})
    }
    composable(DestinasiEntry.route){
      EntryKontakScreen(navigateBack = {
        navController.navigate(
          DestinasiHome.route
        ){
          popUpTo(DestinasiHome.route){
            inclusive
          }
        }
      })
    }
  }
}