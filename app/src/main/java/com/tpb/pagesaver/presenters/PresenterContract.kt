package com.tpb.pagesaver.presenters

import android.content.Intent

/**
 * Created by theo on 30/08/17.
 */
interface PresenterContract {

    fun handleIntent(intent: Intent)

    fun handleBackPress()

}