package com.smoothos.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smoothos.app.ui.theme.*

@Composable
fun AnimationStudioScreen() {
    var selectedStyle by remember { mutableStateOf("Default") }
    var speed by remember { mutableFloatStateOf(1.0f) }
    var duration by remember { mutableFloatStateOf(300f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SmoothDark)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text("Animation Studio", color = SmoothText, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("Smooth animations. Better experience.", color = SmoothTextDim, fontSize = 13.sp)
        Spacer(Modifier.height(20.dp))

        // Tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(SmoothCard)
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("App Launch", "App Exit", "Recent Apps").forEach { tab ->
                val selected = tab == "App Launch"
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (selected) SmoothAccent else Color.Transparent)
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        tab,
                        color = if (selected) Color.Black else SmoothTextDim,
                        fontSize = 13.sp,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Text("Preset Styles", color = SmoothText, fontSize = 15.sp, fontWeight = FontWeight.Medium)
        Spacer(Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            StyleCard("Default", "Smooth & Natural", selectedStyle == "Default") {
                selectedStyle = "Default"
            }
            StyleCard("iOS Style", "Zoom Effect", selectedStyle == "iOS Style") {
                selectedStyle = "iOS Style"
            }
            StyleCard("Ultra Smooth", "Maximum Fluidity", selectedStyle == "Ultra Smooth") {
                selectedStyle = "Ultra Smooth"
            }
        }

        Spacer(Modifier.height(28.dp))

        Text("Animation Speed", color = SmoothText, fontSize = 14.sp)
        Slider(
            value = speed,
            onValueChange = { speed = it },
            valueRange = 0.5f..2.0f,
            colors = SliderDefaults.colors(
                thumbColor = SmoothAccent,
                activeTrackColor = SmoothAccent
            )
        )
        Text("${"%.1f".format(speed)}x", color = SmoothTextDim, fontSize = 12.sp)

        Spacer(Modifier.height(16.dp))

        Text("Duration", color = SmoothText, fontSize = 14.sp)
        Slider(
            value = duration,
            onValueChange = { duration = it },
            valueRange = 100f..800f,
            colors = SliderDefaults.colors(
                thumbColor = SmoothAccent,
                activeTrackColor = SmoothAccent
            )
        )
        Text("${duration.toInt()}ms", color = SmoothTextDim, fontSize = 12.sp)

        Spacer(Modifier.height(28.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = SmoothAccent)
        ) {
            Icon(Icons.Default.PlayArrow, null, tint = Color.Black)
            Spacer(Modifier.width(8.dp))
            Text("Preview Animation", color = Color.Black, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun StyleCard(title: String, subtitle: String, selected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(110.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(if (selected) SmoothAccent.copy(alpha = 0.15f) else SmoothCard)
            .border(
                width = if (selected) 1.5.dp else 0.dp,
                color = if (selected) SmoothAccent else Color.Transparent,
                shape = RoundedCornerShape(14.dp)
            )
            .clickable(onClick = onClick)
            .padding(12.dp)
    ) {
        Text(title, color = SmoothText, fontSize = 13.sp, fontWeight = FontWeight.Medium)
        Text(subtitle, color = SmoothTextDim, fontSize = 11.sp)
    }
}
