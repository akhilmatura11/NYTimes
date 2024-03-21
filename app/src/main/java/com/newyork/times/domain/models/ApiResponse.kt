package com.newyork.times.domain.models

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    val results: List<NewsResponse>,
    val num_results: Int
)

data class NewsResponse(
    val uri: String,
    val title: String,
    val byline: String?,
    @SerializedName("published_date")
    val publishedDate: String,
    val section: String,
    val url: String,
    @SerializedName("abstract")
    val abstractData: String,
    val media: List<Media>?,
    val id: String
)

data class Media(
    val type: String,
    val caption: String,
    val copyright: String,
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetaData>
)

data class MediaMetaData(
    val url: String,
    val format: String,
    val height: Int,
    val width: Int
)

fun ApiResponse.toNewsList(): List<NewsEntity> {
    val list = mutableListOf<NewsEntity>()

    this.results.forEach {
        list.add(
            NewsEntity(
                title = it.title,
                byLine = it.byline,
                publishedDate = it.publishedDate,
                section = it.section,
                url = it.url,
                abstractData = it.abstractData,
                id = it.id,
                imageUrl = it.media?.firstOrNull()?.mediaMetadata?.lastOrNull()?.url.orEmpty(),
                imageCaption = it.media?.firstOrNull()?.caption.orEmpty()
            )
        )
    }
    return list
}