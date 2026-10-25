package com.smoothos.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.smoothos.app.ui.theme.SmoothDark
import com.smoothos.app.ui.theme.SmoothOSTheme
import com.smoothos.app.ui.screens.SmoothOSApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmoothOSTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = SmoothDark
                ) {
                    SmoothOSApp()
                }
            }
        }
    }
}
