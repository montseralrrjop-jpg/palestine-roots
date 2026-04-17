# ProGuard rules for Palestine Roots app

# Opt-out of R8's full mode (can be safer for some reflection-heavy libraries)
#-fullmode

# Keep Compose related classes
-keepclassmembers class * extends androidx.compose.ui.node.RootForTest { *; }

# Keep Room related classes
-keep class androidx.room.RoomDatabase { *; }
-keep class * extends androidx.room.RoomDatabase { *; }
-keep class * { @androidx.room.Dao *; }
-keep class * { @androidx.room.Entity *; }

# Keep Firebase related classes
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**

# Keep Coil related classes
-keep class coil.** { *; }
-dontwarn coil.**

# Keep Kotlin Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembernames class kotlinx.coroutines.android.HandlerContext {
    val handler;
}

# General Keep rules
-keepattributes Signature, Exceptions, *Annotation*
-keep public class * extends android.app.Application
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}
