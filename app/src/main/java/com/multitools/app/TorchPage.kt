package com.multitools.app

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashlightOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun TorchPage() {
    val context = LocalContext.current
    val cameraManager = remember { context.getSystemService(Context.CAMERA_SERVICE) as CameraManager }
    var isOn by remember { mutableStateOf(false) }
    val cameraId = remember {
        runCatching {
            cameraManager.cameraIdList.firstOrNull {
                cameraManager.getCameraCharacteristics(it).get(CameraCharacteristics.FLASH_INFO_AVAILABLE) == true
            }
        }.getOrNull()
    }
    fun toggleTorch() {
        val id = cameraId ?: return
        runCatching { cameraManager.setTorchMode(id, !isOn); isOn = !isOn }
    }
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) toggleTorch()
    }
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Icon(Icons.Default.FlashlightOn, null, Modifier.size(90.dp))
        Spacer(Modifier.height(16.dp))
        Text(if (cameraId == null) "Flash tidak tersedia" else if (isOn) "MENYALA" else "MATI")
        Spacer(Modifier.height(16.dp))
        Button(enabled = cameraId != null, onClick = {
            if (androidx.core.content.ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) toggleTorch()
            else permissionLauncher.launch(android.Manifest.permission.CAMERA)
        }) { Text(if (isOn) "MATIKAN" else "NYALAKAN") }
    }
}
