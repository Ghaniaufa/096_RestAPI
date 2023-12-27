package com.example.consumeapi.repository

import com.example.consumeapi.model.Kontak
import com.example.consumeapi.network.KontakService
import java.io.IOException

interface KontakRepository{

    suspend fun getKontak(): List<Kontak>

    suspend fun insertKontak(kontak: Kontak)

    suspend fun updateKontak(id: Int, kontak: Kontak)

    suspend fun deleteKontak(id: Int)

    suspend fun  getKontakById(id: Int): Kontak
}

class NetworkKontakRepository(
    private val kontakApiService: KontakService
) : KontakRepository{

    override suspend fun getKontak(): List<Kontak> = kontakApiService.getKontak()

    // insert Kontak
    override suspend fun insertKontak(kontak: Kontak) {
        kontakApiService.insertKontak(kontak)
    }

    // update kontak
    override suspend fun updateKontak(id: Int, kontak: Kontak) {
        kontakApiService.updateKontak(id, kontak)
    }

    override suspend fun deleteKontak(id: Int) {
        try {
            val response = kontakApiService.deleteKontak(id)
            if (!response.isSuccessful){
                throw IOException("Failed to delete Kontak. HTTP Status code: ${response.code()}")
            }
            else {
                response.message()
            }
        } catch(e: Exception){
            throw e
        }
    }

    override suspend fun getKontakById(id: Int): Kontak {
        return kontakApiService.getKontakById(id)
    }
}