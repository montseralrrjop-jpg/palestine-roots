package com.palestine.roots.di

import android.content.Context
import com.palestine.roots.data.local.PreferencesManager
import com.palestine.roots.data.local.dao.SiteDao
import com.palestine.roots.data.local.db.PalestineDatabase
import com.palestine.roots.data.repository.SiteRepositoryImpl
import com.palestine.roots.domain.repository.SiteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PalestineDatabase {
        return PalestineDatabase.getDatabase(context)
    }

    @Provides
    fun provideSiteDao(database: PalestineDatabase): SiteDao {
        return database.siteDao()
    }

    @Provides
    @Singleton
    fun provideSiteRepository(siteDao: SiteDao): SiteRepository {
        return SiteRepositoryImpl(siteDao)
    }

    @Provides
    @Singleton
    fun providePreferencesManager(@ApplicationContext context: Context): PreferencesManager {
        return PreferencesManager(context)
    }
}
