package com.tpb.pagesaver

import android.app.Application
import com.tpb.pagesaver.dagger.DaggerMainComponent
import com.tpb.pagesaver.dagger.MainComponent
import com.tpb.pagesaver.dagger.module.DBModule
import com.tpb.pagesaver.dagger.module.MercuryModule

/**
 * Created by theo on 29/08/17.
 */
class App: Application() {

    lateinit var mainComponent: MainComponent
        private set

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.builder()
                .dBModule(DBModule(this, "pages"))
                .mercuryModule(MercuryModule(this, "https://mercury.postlight.com"))
                .build()
    }
}