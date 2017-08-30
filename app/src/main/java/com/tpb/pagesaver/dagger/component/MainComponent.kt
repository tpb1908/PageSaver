package com.tpb.pagesaver.dagger.component

import com.tpb.pagesaver.dagger.module.ConfigModule
import com.tpb.pagesaver.dagger.module.DBModule
import com.tpb.pagesaver.dagger.module.MercuryModule
import com.tpb.pagesaver.views.PageSaveActivity
import com.tpb.pagesaver.views.PageShowActivity
import com.tpb.pagesaver.views.list.PageListActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by theo on 29/08/17.
 */
@Singleton
@Component(modules = arrayOf(DBModule::class, MercuryModule::class, ConfigModule::class))
interface MainComponent {

    fun inject(activity: PageShowActivity)

    fun inject(activity: PageSaveActivity)

    fun inject(activity: PageListActivity)

}