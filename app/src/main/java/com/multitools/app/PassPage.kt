package com.multitools.app
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable fun PassPage(){var n by remember{mutableStateOf(16)};var p by remember{mutableStateOf(PasswordGenerator.generate(n))};Column(verticalArrangement=Arrangement.spacedBy(12.dp)){Text("Length "+n);Slider(n.toFloat(),{n=it.toInt()},valueRange=8f..32f,steps=23);OutlinedTextField(p,{},Modifier.fillMaxWidth(),readOnly=true);Button(onClick={p=PasswordGenerator.generate(n)},Modifier.fillMaxWidth()){Text("GENERATE")}}}