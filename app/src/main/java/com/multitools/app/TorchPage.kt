package com.multitools.app
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashlightOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
@Composable fun TorchPage(){val c=LocalContext.current;val m=remember{c.getSystemService(Context.CAMERA_SERVICE) as CameraManager};var on by remember{mutableStateOf(false)};var ready by remember{mutableStateOf(c.checkSelfPermission("android.permission.CAMERA")==PackageManager.PERMISSION_GRANTED)};val id=remember{runCatching{m.cameraIdList.firstOrNull{m.getCameraCharacteristics(it).get(CameraCharacteristics.FLASH_INFO_AVAILABLE)==true}}.getOrNull()};Column(Modifier.fillMaxSize(),horizontalAlignment=Alignment.CenterHorizontally,verticalArrangement=Arrangement.Center){Icon(Icons.Default.FlashlightOn,null,Modifier.size(90.dp));Text(if(id==null)"No flash" else if(on)"ON" else "OFF");Button(enabled=id!=null,onClick={if(!ready){(c as Activity).requestPermissions(arrayOf("android.permission.CAMERA"),10);ready=true}else{id?.let{v=!on;runCatching{m.setTorchMode(it,v);on=v}}}}){Text(if(ready)if(on)"OFF" else "ON" else "ALLOW CAMERA")}}}