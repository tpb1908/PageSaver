package com.tpb.pagesaver.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by theo on 29/08/17.
 */
class Util {

    companion object {
        private val ISO8061DF = SimpleDateFormat("yyyy-MM-dd'T'")

        fun ISO8061ToDate(time: String): Date {
            val d = Date()
            Log.i("Date string", time + "${time.substring(0, 4)} ${time.substring(5,7)} ${time.substring(8,10)}")
//            d.year = Integer.parseInt(time.substring(0, 4))
//            d.month = Integer.parseInt(time.substring(5,7))
//            d.month = Integer.parseInt(time.substring(8,10))
            return d
        }

        fun ISO8061ToLong(time: String): Long {
            return ISO8061DF.parse(time).time
        }

        fun formatLocally(date: Date): String {
            return java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT).format(date)
        }

    }

}