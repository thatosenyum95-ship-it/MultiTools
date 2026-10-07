package com.multitools.app
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
@Composable fun DevicePage(){Column(Modifier.verticalScroll(rememberScrollState())){listOf("Model" to Build.MODEL,"Brand" to Build.BRAND,"Android" to Build.VERSION.RELEASE,"SDK" to Build.VERSION.SDK_INT,"Device" to Build.DEVICE).forEach{Card(Modifier.fillMaxWidth().padding(bottom=8.dp)){Column(Modifier.padding(12.dp)){Text(it.first);Text(it.second.toString(),fontWeight=FontWeight.Bold)}}}}}