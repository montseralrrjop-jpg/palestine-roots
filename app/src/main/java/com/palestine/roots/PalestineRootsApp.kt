package com.palestine.roots

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * فئة التطبيق الرئيسية.
 * مدعومة بـ Hilt لإدارة حقن التبعية (Dependency Injection).
 */
@HiltAndroidApp
class PalestineRootsApplication : Application()
