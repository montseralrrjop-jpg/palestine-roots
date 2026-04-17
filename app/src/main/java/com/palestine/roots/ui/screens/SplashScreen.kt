package com.palestine.roots.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    // ألوان تراثية فلسطينية
    val earthBrown = Color(0xFF8B7355)
    val oliveGreen = Color(0xFF556B2F)
    val embroideryRed = Color(0xFFA52A2A)
    
    LaunchedEffect(Unit) {
        delay(2500) // عرض الشاشة لمدة 2.5 ثانية
        onNavigateToLogin()
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(earthBrown),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // استخدام أيقونة نظام بدلاً من ملف صورة لضمان التشغيل الفوري
            Icon(
                imageVector = Icons.Default.Eco,
                contentDescription = "شعار فلسطين",
                modifier = Modifier.size(120.dp),
                tint = Color.White
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "جذور فلسطين",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            
            Text(
                text = "اكتشف تراثك الأثري",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.9f),
                modifier = Modifier.padding(top = 8.dp)
            )
            
            // شريط زخرفي مستوحى من التطريز
            Box(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .height(4.dp)
                    .width(200.dp)
                    .background(
                        brush = androidx.compose.ui.graphics.Brush.horizontalGradient(
                            colors = listOf(embroideryRed, oliveGreen, embroideryRed)
                        ),
                        shape = RoundedCornerShape(2.dp)
                    )
            )
        }
    }
}
