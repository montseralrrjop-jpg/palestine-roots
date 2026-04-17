package com.palestine.roots.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * نموذج البيانات الخاص بطبقة الـ Domain.
 * هذا هو الكائن الذي تتعامل معه واجهة المستخدم (UI).
 */
@Parcelize
data class Site(
    val id: String,
    val name: String,
    val nameEn: String,
    val city: String,
    val cityEn: String,
    val description: String,
    val descriptionEn: String,
    val history: String,
    val historyEn: String,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double,
    val category: String,
    val categoryEn: String,
    val foundationYear: String,
    val isFavorite: Boolean
) : Parcelable
