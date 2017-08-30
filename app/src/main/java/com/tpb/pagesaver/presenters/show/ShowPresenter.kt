package com.tpb.pagesaver.presenters.show

import android.content.Intent
import android.util.Log
import com.tpb.pagesaver.data.db.PageDao
import com.tpb.pagesaver.data.preferences.PageViewPreferences
import com.tpb.pagesaver.presenters.Presenter
import com.tpb.pagesaver.util.Keys
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by theo on 30/08/17.
 */
class ShowPresenter @Inject constructor(val dao: PageDao, val prefs: PageViewPreferences) : Presenter<ShowViewContract>, ShowPresenterContract {

    private lateinit var view: ShowViewContract

    private val wrapper = """<html>
            <head>
                <title>Title</title>
                <style type="text/css">
                    body {
                        font-family: monospace;
                        font-size: %1${'$'}fpx;
                        color: #%2${'$'}s;
                        background-color: #%8${'$'}s;
                        margin: %5${'$'}fpx %6${'$'}fpx %5${'$'}fpx %6${'$'}fpx;
                        line-height: %7${'$'}f;
                    }
                    a {color: #%3${'$'}s;}
                    img {display: inline; height: auto; max-width: 100%%;}
                    pre {white-space: pre-wrap;}
                    iframe {width: 90vw; height: 50.625vw;} /* 16:9 */
                </style>
            </head>
            <body>%4${'$'}s</body>
        </html>
        """

    override fun attachView(view: ShowViewContract) {
        this.view = view
    }

    override fun handleIntent(intent: Intent) {
        if (intent.hasExtra(Keys.INTENT_PAGE_ID)) {
            val id: Long = intent.getLongExtra(Keys.INTENT_PAGE_ID, 0)
            dao.getById(id).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                    onNext = {
                        val html = wrapper.format(
                                prefs.getFontSize(),
                                prefs.getFontColor(),
                                prefs.getLinkColor(),
                                it.first().content, prefs.getHorizontalMargin(),
                                prefs.getVerticalMargin(),
                                prefs.getLineHeight(),
                                prefs.getBackgroundColor())
                        Log.i("HTML", html)
                        view.displayHtml(html)
                    }
            )
        }
    }

    override fun handleBackPress() {
    }
}