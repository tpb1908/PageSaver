package com.tpb.pagesaver.presenters.list

import com.tpb.pagesaver.data.models.Page

/**
 * Created by theo on 30/08/17.
 */
interface ListViewContract {

    fun displayPages(pages: Collection<Page>)

}