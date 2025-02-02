package com.meddemo3.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DoctorsResponse(
    @Json(name = "status") val status: String,
    @Json(name = "data") val doctors: List<Doctor>
)

@JsonClass(generateAdapter = true)
data class Doctor(
    @Json(name = "id") val id: Int?,
    @Json(name = "ex_id") val exId: String?,
    @Json(name = "resource_list") val resourceList: List<Int>?,
    @Json(name = "resource_id") val resourceId: Int?,
    @Json(name = "is_schedule") val isSchedule: Int?,
    @Json(name = "fullName") val fullName: String?,
    @Json(name = "degree") val degree: String?,
    @Json(name = "medical_category") val medicalCategory: String?,
    @Json(name = "reception_address") val receptionAddress: String?,
    @Json(name = "price") val price: List<PriceEntry>?,
    @Json(name = "profession") val profession: String?,
    @Json(name = "display_work_title") val displayWorkTitle: String?,
    @Json(name = "information") val information: String?,
    @Json(name = "img") val img: String?,
    @Json(name = "departments") val departments: List<Int>?,
    @Json(name = "clinics") val clinics: List<Int>?,
    @Json(name = "language") val language: List<String>?,
    @Json(name = "is_chat") val isChat: Int?,
    @Json(name = "is_favorite") val isFavorite: Int?,
    @Json(name = "full_name") val fullNameAlt: String?
)

@JsonClass(generateAdapter = true)
data class PriceEntry(
    @Json(name = "clinic") val clinic: Int??,
    @Json(name = "visit") val visit: String?,
    @Json(name = "price") val price: PriceDetail?
)

@JsonClass(generateAdapter = true)
data class PriceDetail(
    @Json(name = "code") val code: String?,
    @Json(name = "price") val price: Int?,
    @Json(name = "title") val title: String?
)
