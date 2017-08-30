package com.tpb.pagesaver

import android.app.Application
import com.tpb.pagesaver.dagger.component.DaggerMainComponent
import com.tpb.pagesaver.dagger.component.MainComponent
import com.tpb.pagesaver.dagger.module.ConfigModule
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
                .configModule(ConfigModule(this))
                .build()
    }
}