package com.tpb.pagesaver.dagger.module

import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.content.Context
import com.tpb.pagesaver.data.db.Database
import com.tpb.pagesaver.data.db.PageDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by theo on 29/08/17.
 */
@Module
class DBModule(val context: Context, val dbName: String, vararg val migrations: Migration) {


    @Provides
    @Singleton
    fun provideDatabase(): Database {
        return Room.databaseBuilder(context, Database::class.java, dbName)
                .addMigrations(*migrations)
                .build()
    }

    @Provides
    @Singleton
    fun providePageDao(database: Database): PageDao {
        return database.pageDao()
    }

}