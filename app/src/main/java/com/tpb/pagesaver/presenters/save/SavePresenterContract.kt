package com.tpb.pagesaver.presenters.save

import com.tpb.pagesaver.presenters.PresenterContract

/**
 * Created by theo on 29/08/17.
 */
interface SavePresenterContract : PresenterContract {


    fun handleShow()

    fun handleDelete()

}