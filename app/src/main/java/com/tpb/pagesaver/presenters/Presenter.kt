package com.tpb.pagesaver.presenters

/**
 * Created by theo on 29/08/17.
 */
interface Presenter<in T> {

    fun attachView(view: T)
}