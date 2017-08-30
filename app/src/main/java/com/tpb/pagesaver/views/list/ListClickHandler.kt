package com.tpb.pagesaver.views.list

import com.tpb.pagesaver.data.models.Page

/**
 * Created by theo on 30/08/17.
 */
interface ListClickHandler {

    fun onOpen(page: Page)

    fun onDelete(page: Page)

}