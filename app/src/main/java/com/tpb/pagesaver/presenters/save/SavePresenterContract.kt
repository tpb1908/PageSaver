package com.tpb.pagesaver.presenters.save

import android.content.Intent

/**
 * Created by theo on 29/08/17.
 */
interface SavePresenterContract {

    fun handleIntent(intent: Intent)

    fun handleShow()

    fun handleDelete()

    fun handleBackPress()

}