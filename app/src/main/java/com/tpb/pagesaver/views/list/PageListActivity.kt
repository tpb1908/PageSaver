package com.tpb.pagesaver.views.list

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.tpb.pagesaver.App
import com.tpb.pagesaver.R
import com.tpb.pagesaver.data.models.Page
import com.tpb.pagesaver.presenters.list.ListPresenter
import com.tpb.pagesaver.presenters.list.ListViewContract
import com.tpb.pagesaver.util.Keys
import com.tpb.pagesaver.views.PageShowActivity
import kotlinx.android.synthetic.main.page_list_layout.*
import javax.inject.Inject

/**
 * Created by theo on 30/08/17.
 */
class PageListActivity : AppCompatActivity(), ListViewContract, ListClickHandler {


    @Inject lateinit var presenter: ListPresenter
    private val adapter = PageListAdapter(this  )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_list_layout)
        (application as App).mainComponent.inject(this)

        pages_recycler.layoutManager = LinearLayoutManager(this)
        pages_recycler.adapter = adapter

        presenter.attachView(this)
    }

    override fun displayPages(pages: Collection<Page>) {
        adapter.clear()
        adapter.addPages(pages)
    }

    override fun onOpen(page: Page) {
        val intent = Intent(this, PageShowActivity::class.java)
        intent.putExtra(Keys.INTENT_PAGE_ID, page.id)
        startActivity(intent)
    }

    override fun onDelete(page: Page) {
    }
}