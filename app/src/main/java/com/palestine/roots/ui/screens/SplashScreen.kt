package com.palestine.roots.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit
) {
    val earthBrown = Color(0xFF8B7355)
    val oliveGreen = Color(0xFF556B2F)
    val embroideryRed = Color(0xFFA52A2A)
    val sandBeige = Color(0xFFF5DEB3)

    // تأثيرات الأنيميشن
    var visible by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.5f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)
    )
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 800)
    )

    LaunchedEffect(Unit) {
        visible = true
        delay(2800)
        onNavigateToLogin()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(earthBrown, oliveGreen)
                )
            )
    ) {
        // دوائر زخرفية في الخلفية
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 60.dp, y = (-100).dp)
                .size(300.dp)
                .alpha(0.08f)
                .background(Color.White, CircleShape)
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = (-80).dp, y = 120.dp)
                .size(250.dp)
                .alpha(0.08f)
                .background(Color.White, CircleShape)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // الشعار مع أنيميشن
            Surface(
                modifier = Modifier
                    .size(140.dp)
                    .scale(scale),
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.15f),
                border = androidx.compose.foundation.BorderStroke(2.dp, Color.White.copy(alpha = 0.3f))
            ) {
                Icon(
                    imageVector = Icons.Default.Eco,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxSize(),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // اسم التطبيق مع أنيميشن
            Text(
                text = "جذور فلسطين",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.alpha(alpha)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "اكتشف تراثك الأثري",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.85f),
                modifier = Modifier.alpha(alpha)
            )

            // شريط زخرفي مستوحى من التطريز الفلسطيني
            Box(
                modifier = Modifier
                    .padding(top = 36.dp)
                    .height(4.dp)
                    .width(200.dp)
                    .alpha(alpha)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(embroideryRed, sandBeige, oliveGreen, sandBeige, embroideryRed)
                        ),
                        shape = RoundedCornerShape(2.dp)
                    )
            )

            // شريط تحميل أنيق
            Spacer(modifier = Modifier.height(40.dp))
            LinearProgressIndicator(
                modifier = Modifier
                    .width(120.dp)
                    .alpha(alpha)
                    .clip(RoundedCornerShape(4.dp)),
                color = sandBeige,
                trackColor = Color.White.copy(alpha = 0.2f)
            )
        }
    }
}
