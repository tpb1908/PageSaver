package com.tpb.pagesaver.presenters.save

import android.content.Intent
import android.util.Log
import com.tpb.pagesaver.data.db.PageDao
import com.tpb.pagesaver.data.models.Page
import com.tpb.pagesaver.data.network.MercuryService
import com.tpb.pagesaver.presenters.Presenter
import com.tpb.pagesaver.util.AlertDialogCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import java.net.URL
import javax.inject.Inject
import kotlin.concurrent.thread

/**
 * Created by theo on 29/08/17.
 */
class SavePresenter @Inject constructor(private val mercury: MercuryService, private val pageDao: PageDao) : Presenter<SaveViewContract>, SavePresenterContract {

    private lateinit var view: SaveViewContract
    private var page: Page? = null

    override fun attachView(view: SaveViewContract) {
        this.view = view
    }

    override fun handleIntent(intent: Intent) {
        if (intent.getStringExtra(Intent.EXTRA_TEXT) != null) {
            val urlString = intent.getStringExtra(Intent.EXTRA_TEXT)
            val url = URL(urlString)
            view.setTitle(url.host)


            //TODO Network check

            mercury.getParsedPage(urlString).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                    onNext = {
                        Log.i("Success", "Loaded page $it")
                        savePage(it)
                        view.hideLoading()
                        view.showPageComplete(it)

                    },
                    onError = {
                        Log.e("Error", "Error loading mercury page", it)
                    }
            )

        } else {
            view.showToast("NO URL")
            view.finishActivity()
        }
    }

    private fun savePage(toSave: Page) {
        thread {
            val pages = pageDao.listPreviousSaves(toSave.url)
            Log.i("Duplicates", "Number of duplicate page ${pages.size}")
            if (pages.isNotEmpty()) {
                view.showUpdateOrDuplicateDialog(object: AlertDialogCallback {
                    override fun onPositive() {
                        thread { pageDao.deletePages(pages) }
                    }

                    override fun onNegative() {
                    }
                }, pages)
            }
            page = toSave.copy(id = pageDao.insertPage(toSave.copy(time = System.currentTimeMillis())))
        }
    }

    override fun providePage(): Page? {
        return page
    }

    override fun handleShow() {

    }

    override fun handleDelete() {
        thread {
            page?.let { pageDao.deletePage(it) }
            view.finishActivity()
        }
    }

    override fun handleBackPress() {
    }
}