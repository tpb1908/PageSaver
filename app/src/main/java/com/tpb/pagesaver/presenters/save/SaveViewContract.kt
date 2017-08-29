package com.tpb.pagesaver.presenters.save

import com.tpb.pagesaver.data.models.Page

/**
 * Created by theo on 29/08/17.
 */
interface SaveViewContract {

    fun showLoading()

    fun hideLoading()

    fun showPageComplete(page: Page)

    fun setTitle(title: String)

    fun showToast(message: String)

    fun showError()

    fun finishActivity()

}