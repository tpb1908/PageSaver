package com.tpb.pagesaver.data.models

import android.annotation.SuppressLint
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import io.mironov.smuggler.AutoParcelable

/**
 * Created by theo on 29/08/17.
 */

@SuppressLint("ParcelCreator")
@Entity(tableName = "page") data class Page(
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long,
        @ColumnInfo(name = "time") val time: Long,
        @SerializedName("title") @ColumnInfo(name = "title") val title: String?,
        @SerializedName("content") @ColumnInfo(name = "content") val content: String?,
        @SerializedName("date_published") @ColumnInfo(name = "published_date") val published: String?,
        @SerializedName("lead_image_url") @ColumnInfo(name = "image_url") val imageURL: String?,
        @SerializedName("dek") @ColumnInfo(name = "dek") val dek: String?,
        @SerializedName("url") @ColumnInfo(name = "url") val url: String,
        @SerializedName("domain") @ColumnInfo(name = "domain") val domain: String,
        @SerializedName("excerpt") @ColumnInfo(name = "excerpt") val excerpt: String?,
        @SerializedName("word_count") @ColumnInfo(name = "word_count") val wordCount: Long) : AutoParcelable {

}