package com.tpb.pagesaver.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.tpb.pagesaver.data.models.Page

/**
 * Created by theo on 29/08/17.
 */

@Database(entities = arrayOf(Page::class), version = 2)

abstract class Database : RoomDatabase() {

    abstract fun pageDao(): PageDao

}