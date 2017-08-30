package com.tpb.pagesaver.presenters.show

import android.content.Intent
import com.tpb.pagesaver.data.db.PageDao
import com.tpb.pagesaver.presenters.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by theo on 30/08/17.
 */
class ShowPresenter @Inject constructor(val dao: PageDao) : Presenter<ShowViewContract>, ShowPresenterContract {

    private lateinit var view: ShowViewContract

    private val wrapper = """<html>
            <head>
                <title>Title</title>
                <style type="text/css">
                    body {
                        font-family: materialistic;
                        font-size: %1${'$'}fpx;
                        color: #%2${'$'}s;
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
        dao.getAllItems().observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onNext = {
                    val html = wrapper.format(12.0,"000000","FFFFFF",it.first().content, 20.0, 20.0, 1.0)
                    view.displayHtml(html)
                }
        )
    }

    override fun handleIntent(intent: Intent) {

    }

    override fun handleBackPress() {
    }
}