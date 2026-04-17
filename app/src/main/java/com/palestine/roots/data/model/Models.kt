package com.palestine.roots.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * نموذج بيانات للموقع الأثري
 */
@Parcelize
data class Site(
    val id: String = "",
    val nameAr: String = "",
    val nameEn: String = "",
    val city: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val audioUrl: String? = null,
    val history: String = ""
) : Parcelable

/**
 * نموذج بيانات المستخدم
 */
data class User(
    val uid: String = "",
    val email: String = "",
    val displayName: String = "",
    val photoUrl: String = "",
    val lastLogin: Long = System.currentTimeMillis()
)
