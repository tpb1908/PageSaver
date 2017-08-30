package com.tpb.pagesaver.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tpb.pagesaver.App
import com.tpb.pagesaver.R
import com.tpb.pagesaver.presenters.show.ShowPresenter
import com.tpb.pagesaver.presenters.show.ShowViewContract
import kotlinx.android.synthetic.main.page_view_layout.*
import javax.inject.Inject

/**
 * Created by theo on 29/08/17.
 */
class PageShowActivity : AppCompatActivity(), ShowViewContract {


    @Inject lateinit var presenter: ShowPresenter

//    @font-face {
//        font-family: materialistic;
//        src: url("file:///android_asset/%1${'$'}s")}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_view_layout)

        (application as App).mainComponent.inject(this)

        //pageWebView.settings.javaScriptEnabled = true

        presenter.attachView(this)

    }

    override fun displayHtml(html: String) {
        runOnUiThread { pageWebView.loadData(html, "text/html", "UTF-8") }

    }
}