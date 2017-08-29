package com.tpb.pagesaver.presenters.save

import android.content.Intent
import android.util.Log
import com.tpb.pagesaver.data.network.MercuryService
import com.tpb.pagesaver.presenters.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import java.net.URL
import javax.inject.Inject

/**
 * Created by theo on 29/08/17.
 */
class SavePresenter @Inject constructor(private val mercury: MercuryService) : Presenter<SaveViewContract>, SavePresenterContract {

    lateinit var view: SaveViewContract

    override fun attachView(view: SaveViewContract) {
        this.view = view
    }

    override fun handleIntent(intent: Intent) {
        if (intent.getStringExtra(Intent.EXTRA_TEXT) != null) {
            val urlString = intent.getStringExtra(Intent.EXTRA_TEXT)
            val url = URL(urlString)
            view.setTitle(url.host)

            mercury.getParsedPage(urlString).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                    onNext = {
                        Log.i("Success", "Loaded page $it")
                        view.hideLoading()
                        view.showPageComplete(it)
                    },
                    onError = {
                        Log.e("Error", "Error loading mercury page")
                    }
            )

        } else {
            view.showToast("NO URL")
            view.finishActivity()
        }
    }

    override fun handleShow() {
    }

    override fun handleDelete() {
    }

    override fun handleBackPress() {
    }
}