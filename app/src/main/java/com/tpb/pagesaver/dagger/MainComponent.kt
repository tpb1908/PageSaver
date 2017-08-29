package com.tpb.pagesaver.dagger

import com.tpb.pagesaver.dagger.module.DBModule
import com.tpb.pagesaver.dagger.module.MercuryModule
import com.tpb.pagesaver.main.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by theo on 29/08/17.
 */
@Singleton
@Component(modules = arrayOf(DBModule::class, MercuryModule::class))
interface MainComponent {

    fun inject(activity: MainActivity)

}