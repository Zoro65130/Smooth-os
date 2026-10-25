package com.smoothos.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.smoothos.app.ui.theme.*

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SmoothDark)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "SmoothOS",
                    color = SmoothText,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Your phone. Smoother.",
                    color = SmoothTextDim,
                    fontSize = 13.sp
                )
            }
            IconButton(onClick = { /* settings */ }) {
                Icon(Icons.Default.Settings, contentDescription = null, tint = SmoothTextDim)
            }
        }

        Spacer(Modifier.height(20.dp))

        // Featured card - Animation Studio
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(
                    Brush.horizontalGradient(
                        listOf(Color(0xFF1A237E), Color(0xFF4A148C))
                    )
                )
                .clickable { navController.navigate(Screen.Studio.route) }
                .padding(20.dp)
        ) {
            Column {
                Text(
                    "Animation Studio",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    "Customize your animations\nand make your device feel alive.",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 13.sp
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Tools grid
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            ToolCard("Phone Cleaner", "Free up space", Icons.Default.CleaningServices, SmoothGreen, Modifier.weight(1f))
            ToolCard("Storage Analyzer", "Check storage usage", Icons.Default.Analytics, SmoothAccent2, Modifier.weight(1f))
        }
        Spacer(Modifier.height(12.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            ToolCard("App Manager", "Manage your apps", Icons.Default.Apps, SmoothPink, Modifier.weight(1f)) {
                navController.navigate(Screen.Apps.route)
            }
            ToolCard("Control Center", "Quick settings", Icons.Default.Tune, SmoothBlue, Modifier.weight(1f))
        }

        Spacer(Modifier.height(24.dp))

        Text("Quick Tools", color = SmoothText, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Spacer(Modifier.height(12.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            QuickTool(Icons.Default.Folder, "Files") { navController.navigate(Screen.Files.route) }
            QuickTool(Icons.Default.Download, "Downloads") { navController.navigate(Screen.Files.route) }
            QuickTool(Icons.Default.Settings, "Settings") {}
        }

        Spacer(Modifier.height(24.dp))
    }
}

@Composable
fun ToolCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    accent: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(SmoothCard)
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Icon(icon, contentDescription = null, tint = accent, modifier = Modifier.size(28.dp))
        Spacer(Modifier.height(10.dp))
        Text(title, color = SmoothText, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        Text(subtitle, color = SmoothTextDim, fontSize = 11.sp)
    }
}

@Composable
fun QuickTool(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(SmoothCard),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = label, tint = SmoothAccent, modifier = Modifier.size(24.dp))
        }
        Spacer(Modifier.height(6.dp))
        Text(label, color = SmoothTextDim, fontSize = 12.sp)
    }
}
