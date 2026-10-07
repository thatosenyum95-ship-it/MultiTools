package com.multitools.app
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.multitools.app.ui.theme.MultiToolsTheme
class MainActivity:ComponentActivity(){override fun onCreate(b:Bundle?){super.onCreate(b);setContent{MultiToolsTheme{FunctionalApp()}}}}