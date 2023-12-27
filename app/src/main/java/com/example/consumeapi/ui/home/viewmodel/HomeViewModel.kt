package com.example.consumeapi.ui.home.viewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.consumeapi.model.Kontak
import com.example.consumeapi.repository.KontakRepository
import kotlinx.coroutines.launch


sealed class KontakUIState{
    data class Success(val kontak: List<Kontak>) : KontakUIState()
    object Error : KontakUIState()
    object Loading : KontakUIState()
}


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class HomeViewModel(private val kontakRepository: KontakRepository) : ViewModel(){
    var kontakUIState: KontakUIState by mutableStateOf(KontakUIState.Loading)
        private set

    init {
        getKontak()
    }


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getKontak(){
        viewModelScope.launch {
            kontakUIState = KontakUIState.Loading
            kontakUIState = try {
                KontakUIState.Success(kontakRepository.getKontak())
            } catch (e: java.io.IOException){
                KontakUIState.Error
            } catch (e: HttpException){
                KontakUIState.Error
            }
        }
    }
    fun deleteKontak(id : Int){
        viewModelScope.launch {
            try {
                kontakRepository.deleteKontak(id)
            } catch (e: java.io.IOException){
                KontakUIState.Error
            }catch (e: HttpException) {
                KontakUIState.Error
            }
        }
    }
}



