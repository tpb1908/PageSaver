package com.tpb.pagesaver.presenters.save

import com.tpb.pagesaver.data.models.Page
import com.tpb.pagesaver.presenters.PresenterContract

/**
 * Created by theo on 29/08/17.
 */
interface SavePresenterContract : PresenterContract {

    fun providePage(): Page?

    fun handleShow()

    fun handleDelete()

}