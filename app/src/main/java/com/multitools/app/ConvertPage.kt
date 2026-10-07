package com.multitools.app
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable fun ConvertPage(){var x by remember{mutableStateOf("1")};var r by remember{mutableStateOf("")};Column(verticalArrangement=Arrangement.spacedBy(10.dp)){Text("Meter to Centimeter");OutlinedTextField(x,{x=it},Modifier.fillMaxWidth());Button(onClick={r=((x.toDoubleOrNull()?:0.0)*100).toString()},Modifier.fillMaxWidth()){Text("CONVERT")};Text(r,style=MaterialTheme.typography.headlineSmall)}}