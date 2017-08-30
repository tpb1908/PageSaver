package com.tpb.pagesaver.presenters.list

import android.content.Intent
import com.tpb.pagesaver.data.db.PageDao
import com.tpb.pagesaver.presenters.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by theo on 30/08/17.
 */
class ListPresenter @Inject constructor(val pageDao: PageDao): Presenter<ListViewContract>, ListPresenterContract {

    private lateinit var view: ListViewContract

    override fun attachView(view: ListViewContract) {
        this.view = view
        pageDao.getAllItems().observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onNext = {
                    view.displayPages(it)
                }
        )
    }

    override fun handleIntent(intent: Intent) {
    }

    override fun handleBackPress() {
    }
}