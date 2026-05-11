package com.palestine.roots.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sites")
data class SiteEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    @ColumnInfo(name = "name_en")
    val nameEn: String,
    val city: String,
    @ColumnInfo(name = "city_en")
    val cityEn: String,
    val description: String,
    @ColumnInfo(name = "description_en")
    val descriptionEn: String,
    val history: String,
    @ColumnInfo(name = "history_en")
    val historyEn: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double,
    val category: String,
    @ColumnInfo(name = "category_en")
    val categoryEn: String,
    @ColumnInfo(name = "foundation_year")
    val foundationYear: String?,
    @ColumnInfo(name = "is_favorite", defaultValue = "0")
    val isFavorite: Boolean = false
)
