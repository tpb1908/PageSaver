package com.tpb.pagesaver.data.network

import com.tpb.pagesaver.data.models.Page
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by theo on 29/08/17.
 */
interface MercuryService {

    @GET("parser")
    fun getParsedPage(@Query("url") url: String): Observable<Page>

}