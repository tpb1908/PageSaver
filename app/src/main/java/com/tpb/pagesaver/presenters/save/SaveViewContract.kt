package com.tpb.pagesaver.presenters.save

import com.tpb.pagesaver.data.models.Page
import com.tpb.pagesaver.util.AlertDialogCallback

/**
 * Created by theo on 29/08/17.
 */
interface SaveViewContract {

    fun showLoading()

    fun hideLoading()

    fun showPageComplete(page: Page)

    fun setTitle(title: String)

    fun showToast(message: String)

    fun showUpdateOrDuplicateDialog(listener: AlertDialogCallback, pages: List<Page>)

    fun showError()

    fun finishActivity()

}