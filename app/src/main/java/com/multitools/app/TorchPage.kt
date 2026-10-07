package com.multitools.app
import android.content.Context
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
@Composable fun TorchPage(){val c=LocalContext.current;val m=remember{c.getSystemService(Context.CAMERA_SERVICE) as CameraManager};var on by remember{mutableStateOf(false)};val id=remember{m.cameraIdList.firstOrNull{m.getCameraCharacteristics(it).get(CameraCharacteristics.FLASH_INFO_AVAILABLE)==true}};Column(Modifier.fillMaxSize(),horizontalAlignment=Alignment.CenterHorizontally,verticalArrangement=Arrangement.Center){Icon(Icons.Default.FlashlightOn,null,Modifier.size(90.dp));Text(if(on)"ON" else "OFF");Button(enabled=id!=null,onClick={id?.let{v=!on;runCatching{m.setTorchMode(it,v);on=v}}}){Text(if(on)"OFF" else "ON")}}}