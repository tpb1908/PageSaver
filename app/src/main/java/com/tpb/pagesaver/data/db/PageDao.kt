package com.tpb.pagesaver.data.db

import android.arch.persistence.room.*
import com.tpb.pagesaver.data.models.Page
import io.reactivex.Flowable

/**
 * Created by theo on 29/08/17.
 */
@Dao
interface PageDao {


    @Query("SELECT * FROM page")
    fun getAllItems(): Flowable<List<Page>>

    @Query("SELECT * FROM page WHERE id = :id LIMIT 1")
    fun getById(id: Long): Flowable<List<Page>>

    @Query("SELECT * FROM page WHERE url = :url")
    fun listPreviousSaves(url: String): List<Page>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPage(page: Page): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePage(page: Page)

    @Delete
    fun deletePage(page: Page)

    @Delete
    fun deletePages(pages: Collection<Page>)

}