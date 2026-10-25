package com.smoothos.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smoothos.app.ui.theme.*

data class FileEntry(
    val name: String,
    val info: String,
    val icon: ImageVector,
    val color: Color
)

@Composable
fun FilesScreen() {
    val folders = listOf(
        FileEntry("DCIM", "12 items", Icons.Default.Folder, Color(0xFFFFC107)),
        FileEntry("Pictures", "34 items", Icons.Default.Folder, Color(0xFF42A5F5)),
        FileEntry("Videos", "8 items", Icons.Default.Folder, Color(0xFFAB47BC)),
        FileEntry("Music", "6 items", Icons.Default.Folder, Color(0xFFEC407A)),
        FileEntry("Documents", "14 items", Icons.Default.Folder, Color(0xFF66BB6A)),
        FileEntry("Android", "6 items", Icons.Default.Folder, Color(0xFF26C6DA)),
        FileEntry("Download", "2.4 GB", Icons.Default.Download, Color(0xFF66BB6A)),
        FileEntry("Apps", "12.6 GB", Icons.Default.Apps, Color(0xFFFFA726))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SmoothDark)
            .padding(16.dp)
    ) {
        Text("Files", color = SmoothText, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))

        // Search bar
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search files...", color = SmoothTextDim) },
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

        // Storage summary chips
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            StorageChip("Internal", "128 GB / 256 GB", SmoothAccent)
            StorageChip("Downloads", "2.4 GB", SmoothGreen)
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(folders) { entry ->
                FileRow(entry)
            }
        }
    }
}

@Composable
fun StorageChip(label: String, value: String, color: Color) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(SmoothCard)
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        Text(label, color = SmoothTextDim, fontSize = 11.sp)
        Text(value, color = color, fontSize = 13.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun FileRow(entry: FileEntry) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SmoothCard)
            .clickable { }
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(entry.color.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(entry.icon, null, tint = entry.color, modifier = Modifier.size(22.dp))
        }
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Text(entry.name, color = SmoothText, fontSize = 15.sp, fontWeight = FontWeight.Medium)
            Text(entry.info, color = SmoothTextDim, fontSize = 12.sp)
        }
        Icon(Icons.Default.ChevronRight, null, tint = SmoothTextDim)
    }
}
