package com.smoothos.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smoothos.app.ui.theme.*

data class AppItem(
    val name: String,
    val icon: ImageVector,
    val color: Color
)

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun AllAppsScreen() {
    val apps = listOf(
        AppItem("YouTube", Icons.Default.PlayArrow, Color(0xFFFF0000)),
        AppItem("Chrome", Icons.Default.Public, Color(0xFF4285F4)),
        AppItem("Instagram", Icons.Default.CameraAlt, Color(0xFFE1306C)),
        AppItem("Spotify", Icons.Default.MusicNote, Color(0xFF1DB954)),
        AppItem("Discord", Icons.Default.Chat, Color(0xFF5865F2)),
        AppItem("Telegram", Icons.Default.Send, Color(0xFF0088CC)),
        AppItem("WhatsApp", Icons.Default.Message, Color(0xFF25D366)),
        AppItem("Play Store", Icons.Default.Shop, Color(0xFF34A853)),
        AppItem("Camera", Icons.Default.PhotoCamera, Color(0xFF90A4AE)),
        AppItem("Gallery", Icons.Default.PhotoLibrary, Color(0xFF5C6BC0)),
        AppItem("Files", Icons.Default.Folder, Color(0xFFFFA726)),
        AppItem("Settings", Icons.Default.Settings, Color(0xFF78909C))
    )

    var showMenuFor by remember { mutableStateOf<AppItem?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SmoothDark)
            .padding(16.dp)
    ) {
        Text("All Apps", color = SmoothText, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search apps...", color = SmoothTextDim) },
            leadingIcon = { Icon(Icons.Default.Search, null, tint = SmoothTextDim) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = SmoothAccent,
                unfocusedBorderColor = SmoothCard,
                focusedContainerColor = SmoothCard,
                unfocusedContainerColor = SmoothCard,
                cursorColor = SmoothAccent,
                focusedTextColor = SmoothText,
                unfocusedTextColor = SmoothText
            ),
            singleLine = true
        )

        Spacer(Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(apps) { app ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .combinedClickable(
                            onClick = { },
                            onLongClick = { showMenuFor = app }
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(app.color.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(app.icon, null, tint = app.color, modifier = Modifier.size(28.dp))
                    }
                    Spacer(Modifier.height(6.dp))
                    Text(
                        app.name,
                        color = SmoothText,
                        fontSize = 11.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            }
        }
    }

    // Long-press context menu (uninstall flow)
    showMenuFor?.let { app ->
        AlertDialog(
            onDismissRequest = { showMenuFor = null },
            containerColor = SmoothCard,
            title = {
                Text(app.name, color = SmoothText, fontWeight = FontWeight.SemiBold)
            },
            text = {
                Column {
                    TextButton(onClick = { showMenuFor = null }) {
                        Icon(Icons.Default.Info, null, tint = SmoothAccent)
                        Spacer(Modifier.width(8.dp))
                        Text("App Info", color = SmoothText)
                    }
                    TextButton(onClick = {
                        // Simulated uninstall flow — in real app would use PackageInstaller / Intent
                        showMenuFor = null
                    }) {
                        Icon(Icons.Default.Delete, null, tint = Color(0xFFEF5350))
                        Spacer(Modifier.width(8.dp))
                        Text("Uninstall", color = Color(0xFFEF5350))
                    }
                    TextButton(onClick = { showMenuFor = null }) {
                        Icon(Icons.Default.Home, null, tint = SmoothTextDim)
                        Spacer(Modifier.width(8.dp))
                        Text("Remove from Home", color = SmoothTextDim)
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showMenuFor = null }) {
                    Text("Cancel", color = SmoothTextDim)
                }
            }
        )
    }
}
