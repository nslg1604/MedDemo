package com.meddemo3.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ActualsResponse(
    @Json(name = "status") val status: String,
    @Json(name = "data") val stories: List<Story>
)

@JsonClass(generateAdapter = true)
data class Story(
    @Json(name = "type") val type: String?,
    @Json(name = "linkType") val linkType: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "link") val link: String?,
    @Json(name = "img") val img: String?,
    @Json(name = "img_xs") val imgXs: String?,
    @Json(name = "item") val storyItem: StoryItem?
)

@JsonClass(generateAdapter = true)
data class StoryItem(
    @Json(name = "images") val images: List<Image>?,
    @Json(name = "storiesItems") val storiesItems: StoriesItems?
)

@JsonClass(generateAdapter = true)
data class Image(
    @Json(name = "url") val url: String?,
    @Json(name = "viewed") val viewed: Boolean?,
    @Json(name = "type") val type: String?
)

@JsonClass(generateAdapter = true)
data class StoriesItems(
    @Json(name = "type") val type: String?,
    @Json(name = "link") val link: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "text") val text: String?,
    @Json(name = "bgColor") val bgColor: String?,
    @Json(name = "class") val className: String?
)
