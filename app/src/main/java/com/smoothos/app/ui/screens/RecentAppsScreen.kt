package com.smoothos.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smoothos.app.ui.theme.*

@Composable
fun RecentAppsScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SmoothDark)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Recent Apps", color = SmoothText, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            IconButton(onClick = onBack) {
                Icon(Icons.Default.Close, null, tint = SmoothTextDim)
            }
        }

        Spacer(Modifier.height(20.dp))

        // Placeholder recent cards
        listOf("YouTube", "Chrome", "Gallery").forEach { name ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(SmoothCard)
                    .padding(16.dp)
            ) {
                Text(name, color = SmoothText, fontWeight = FontWeight.Medium)
            }
            Spacer(Modifier.height(12.dp))
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(26.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A2235))
        ) {
            Icon(Icons.Default.Delete, null, tint = SmoothAccent)
            Spacer(Modifier.width(8.dp))
            Text("Clear All", color = SmoothAccent)
        }
    }
}
