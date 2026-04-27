package com.palestine.roots.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.palestine.roots.data.local.dao.SiteDao
import com.palestine.roots.data.local.entity.SiteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.nio.charset.Charset

@Database(entities = [SiteEntity::class], version = 1, exportSchema = false)
abstract class PalestineDatabase : RoomDatabase() {

    abstract fun siteDao(): SiteDao

    companion object {
        @Volatile
        private var INSTANCE: PalestineDatabase? = null

        fun getDatabase(context: Context): PalestineDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PalestineDatabase::class.java,
                    "palestine_roots_database"
                )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // ملء البيانات عند الإنشاء الأول
                        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
                            val dao = getDatabase(context).siteDao()
                            populateFromAssets(context, dao)
                        }
                    }
                })
                .build()
                INSTANCE = instance
                instance
            }
        }

        private suspend fun populateFromAssets(context: Context, dao: SiteDao) {
            try {
                val inputStream = context.assets.open("palestine_sites.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                val jsonString = String(buffer, Charset.forName("UTF-8"))
                val jsonArray = JSONArray(jsonString)

                val sites = mutableListOf<SiteEntity>()
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    sites.add(
                        SiteEntity(
                            id = obj.getString("id"),
                            name = obj.getString("name"),
                            nameEn = obj.getString("name_en"),
                            city = obj.getString("city"),
                            cityEn = obj.getString("city_en"),
                            description = obj.getString("description"),
                            descriptionEn = obj.getString("description_en"),
                            history = obj.getString("history"),
                            historyEn = obj.getString("history_en"),
                            imageUrl = obj.getString("imageUrl"),
                            latitude = obj.getDouble("latitude"),
                            longitude = obj.getDouble("longitude"),
                            category = obj.getString("category"),
                            categoryEn = obj.getString("category_en"),
                            foundationYear = obj.getString("foundationYear")
                        )
                    )
                }
                dao.insertSites(sites)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
