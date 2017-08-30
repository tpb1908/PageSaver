package com.tpb.pagesaver.dagger.module

import android.content.Context
import com.tpb.pagesaver.data.preferences.PageViewPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by theo on 30/08/17.
 */
@Module
class ConfigModule(val context: Context) {

    @Provides
    @Singleton
    fun providePageViewPreferences(): PageViewPreferences {
        return PageViewPreferences(context)
    }

}