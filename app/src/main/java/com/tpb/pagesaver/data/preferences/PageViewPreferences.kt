package com.tpb.pagesaver.data.preferences

import android.content.Context

/**
 * Created by theo on 30/08/17.
 */
class PageViewPreferences(val context: Context) {

    fun getFontFamily(): String {
        return "sans-serif"
    }

    fun getFontSize(): Float {
        return 16f
    }

    fun getFontColor(): String {
       return "FFFFFF"
    }

    fun getBackgroundColor(): String {
        return "303030"
    }

    fun getHorizontalMargin(): Float {
        return 0f
    }

    fun getVerticalMargin(): Float {
        return 0f
    }

    fun getLineHeight(): Float {
        return 1f
    }

    fun getLinkColor(): String {
        return "252ace"
    }


}