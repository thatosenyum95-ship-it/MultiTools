package com.multitools.app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.round

@Composable
fun ConvertPage() {
    val modes = listOf("Panjang" to "m → cm", "Berat" to "kg → g", "Suhu" to "°C → °F")
    var mode by remember { mutableStateOf(0) }
    var input by remember { mutableStateOf("1") }
    var result by remember { mutableStateOf("") }
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("Konverter", style = MaterialTheme.typography.headlineSmall)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            modes.forEachIndexed { index, item ->
                FilterChip(selected = mode == index, onClick = { mode = index; result = "" }, label = { Text(item.first) })
            }
        }
        Text(modes[mode].second)
        OutlinedTextField(input, { input = it }, Modifier.fillMaxWidth(), label = { Text("Nilai") })
        Button(onClick = {
            val value = input.toDoubleOrNull()
            result = if (value == null) "Nilai tidak valid" else when (mode) {
                0 -> value.toString() + " m = " + (value * 100) + " cm"
                1 -> value.toString() + " kg = " + (value * 1000) + " g"
                else -> value.toString() + " °C = " + (round((value * 9 / 5 + 32) * 100) / 100) + " °F"
            }
        }, Modifier.fillMaxWidth()) { Text("CONVERT") }
        Text(result, style = MaterialTheme.typography.headlineSmall)
    }
}
