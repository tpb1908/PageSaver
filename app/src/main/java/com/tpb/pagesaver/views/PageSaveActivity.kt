package com.tpb.pagesaver.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.format.DateUtils
import android.view.View
import android.widget.Toast
import com.tpb.pagesaver.App
import com.tpb.pagesaver.R
import com.tpb.pagesaver.data.models.Page
import com.tpb.pagesaver.data.network.MercuryService
import com.tpb.pagesaver.presenters.save.SavePresenter
import com.tpb.pagesaver.presenters.save.SaveViewContract
import com.tpb.pagesaver.util.AlertDialogCallback
import com.tpb.pagesaver.util.Keys
import com.tpb.pagesaver.util.Util
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
        addListeners()

    }

    private fun addListeners() {
        deleteButton.setOnClickListener {
            AlertDialog.Builder(this)
                    .setMessage(R.string.confirm_delete)
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton(R.string.action_delete, {di, i -> presenter.handleDelete()})
                    .show()
        }
        showButton.setOnClickListener {
            val intent = Intent(this, PageShowActivity::class.java)
            if (presenter.providePage() != null) {
                intent.putExtra(Keys.INTENT_PAGE_ID, presenter.providePage()?.id)
                startActivity(intent)
            }
        }

    }

    override fun showLoading() {
        downloadProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        downloadProgress.visibility = View.GONE
    }

    override fun showPageComplete(page: Page) {
        setFinishOnTouchOutside(true)
        completeInfo.visibility = View.VISIBLE
        if (page.published != null) {
            pageDate.text = Util.formatLocally(Util.ISO8061ToDate(page.published))
        } else {
            pageDate.visibility = View.GONE
        }
        pageTitle.text = page.title
        pageExcerpt.text = page.excerpt
    }

    override fun showUpdateOrDuplicateDialog(listener: AlertDialogCallback, pages: List<Page>) {
        runOnUiThread {
            AlertDialog.Builder(this)
                    .setTitle(R.string.title_duplicates_found)
                    .setMessage(resources.getQuantityString(
                            R.plurals.message_merge_duplicates,
                            pages.size,
                            pages.size,
                            DateUtils.getRelativeTimeSpanString(pages.first().time))
                    )
                    .setPositiveButton(R.string.action_merge, {_, _ -> listener.onPositive()})
                    .setNegativeButton(R.string.action_duplicate, {_, _ -> listener.onNegative()})
                    .show()
        }
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