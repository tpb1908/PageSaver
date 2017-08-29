package com.tpb.pagesaver.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.tpb.pagesaver.App
import com.tpb.pagesaver.R
import com.tpb.pagesaver.data.models.Page
import com.tpb.pagesaver.data.network.MercuryService
import com.tpb.pagesaver.presenters.save.SavePresenter
import com.tpb.pagesaver.presenters.save.SaveViewContract
import kotlinx.android.synthetic.main.page_save_layout.*
import javax.inject.Inject

/**
 * Created by theo on 29/08/17.
 */
class PageSaveActivity: AppCompatActivity(), SaveViewContract {

    @Inject lateinit var presenter: SavePresenter

    @Inject lateinit var mercury: MercuryService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_save_layout)
        setFinishOnTouchOutside(false)
        (application as App).mainComponent.inject(this)

        presenter.attachView(this)

        presenter.handleIntent(intent)


    }

    override fun showLoading() {
        downloadProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        downloadProgress.visibility = View.GONE
    }

    override fun showPageComplete(page: Page) {
        completeInfo.visibility = View.VISIBLE
        pageTitle.text = page.title
        pageExcerpt.text = page.excerpt
    }

    override fun showError() {
    }

    override fun finishActivity() {
        finish()
    }

    override fun setTitle(titleString: String) {
        title = titleString
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}