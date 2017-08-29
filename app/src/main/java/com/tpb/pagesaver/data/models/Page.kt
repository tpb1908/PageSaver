package com.tpb.pagesaver.data.models

import android.annotation.SuppressLint
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import io.mironov.smuggler.AutoParcelable

/**
 * Created by theo on 29/08/17.
 */

@SuppressLint("ParcelCreator")
@Entity(tableName = "page") data class Page(
        @SerializedName("id") @PrimaryKey(autoGenerate = true) val id: Long,
        @SerializedName("time") val time: Long = System.currentTimeMillis(),
        @SerializedName("title") val title: String,
        @SerializedName("content") val content: String,
        @SerializedName("pdate") val published: String,
        @SerializedName("image_url") val imageURL: String,
        @SerializedName("dek") val dek: String,
        @SerializedName("url") val url: String,
        @SerializedName("domain") val domain: String,
        @SerializedName("excerpt") val excerpt: String,
        @SerializedName("word_count") val wordCount: Long) : AutoParcelable