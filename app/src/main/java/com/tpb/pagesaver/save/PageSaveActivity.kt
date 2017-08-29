package com.tpb.pagesaver.save

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.tpb.pagesaver.R
import com.tpb.pagesaver.data.network.MercuryService
import java.net.URL
import javax.inject.Inject

/**
 * Created by theo on 29/08/17.
 */
class PageSaveActivity: AppCompatActivity() {

    @Inject lateinit var mercury: MercuryService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_save_layout)
        setFinishOnTouchOutside(false)

        if (intent?.getStringExtra(Intent.EXTRA_TEXT) != null) {
            val urlString = intent.getStringExtra(Intent.EXTRA_TEXT)
            val url = URL(urlString)
            title = url.host

        } else {
            Toast.makeText(this, "NO URL", Toast.LENGTH_SHORT).show()
            finish()
        }



    }
}