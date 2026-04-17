package com.palestine.roots.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.palestine.roots.data.model.Site
import kotlinx.coroutines.tasks.await

class SiteRepository {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("sites")
    
    // جلب جميع المواقع
    suspend fun getAllSites(): List<Site> {
        return try {
            val snapshot = collection.get().await()
            snapshot.documents.mapNotNull { it.toObject<Site>() }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    // جلب مواقع مدينة معينة
    suspend fun getSitesByCity(city: String): List<Site> {
        return try {
            val snapshot = collection
                .whereEqualTo("city", city)
                .get()
                .await()
            snapshot.documents.mapNotNull { it.toObject<Site>() }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    // جلب موقع محدد حسب ID
    suspend fun getSiteById(siteId: String): Site? {
        return try {
            val doc = collection.document(siteId).get().await()
            doc.toObject<Site>()
        } catch (e: Exception) {
            null
        }
    }
}
