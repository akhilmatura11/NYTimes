package com.newyork.times.domain.models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class NewsEntity(
    val abstractData: String,
    val title: String,
    val byLine: String?,
    val publishedDate: String,
    val section: String,
    val url: String,
    val imageUrl: String?,
    val imageCaption: String?,
    @PrimaryKey
    @NonNull
    val id: String
) : Parcelable


