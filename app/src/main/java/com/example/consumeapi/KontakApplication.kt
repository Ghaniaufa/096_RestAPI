package com.example.consumeapi

import android.app.Application
import com.example.consumeapi.repository.AppContainer
import com.example.consumeapi.repository.KontakContainer

class KontakApplication : Application(){
    /** AppContainer instance used by the res of classes to obtain dependencies */

    lateinit var container: AppContainer
    override fun onCreate(){
        super.onCreate()
        container = KontakContainer()
    }
}