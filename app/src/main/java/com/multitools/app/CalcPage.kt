package com.multitools.app
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable fun CalcPage(){var e by remember{mutableStateOf("")};var r by remember{mutableStateOf("")};val b=listOf("7","8","9","+","4","5","6","-","1","2","3","*","0",".","C","/");Column(verticalArrangement=Arrangement.spacedBy(8.dp)){Text(if(e.isEmpty())"0" else e,style=MaterialTheme.typography.headlineMedium);Text(r);LazyVerticalGrid(GridCells.Fixed(4),Modifier.weight(1f),verticalArrangement=Arrangement.spacedBy(6.dp),horizontalArrangement=Arrangement.spacedBy(6.dp)){items(b){x->Button(onClick={if(x=="C"){e="";r=""}else e+=x}){Text(x)}}};Button(onClick={r=CalculatorEngine.evaluate(e)},Modifier.fillMaxWidth()){Text("HITUNG")}}}