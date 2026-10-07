package com.multitools.app

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlin.math.atan2
import kotlin.math.roundToInt

@Composable
fun LevelPage() {
    val context = LocalContext.current
    var x by remember { mutableStateOf(0f) }
    var y by remember { mutableStateOf(0f) }
    var available by remember { mutableStateOf(true) }
    DisposableEffect(Unit) {
        val manager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        available = sensor != null
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) { x = event.values[0]; y = event.values[1] }
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
        }
        if (sensor != null) manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)
        onDispose { manager.unregisterListener(listener) }
    }
    val xAngle = Math.toDegrees(atan2(x.toDouble(), 9.81)).roundToInt()
    val yAngle = Math.toDegrees(atan2(y.toDouble(), 9.81)).roundToInt()
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        if (!available) Text("Sensor accelerometer tidak tersedia")
        else {
            Text("Sumbu X: $xAngle°", style = MaterialTheme.typography.headlineMedium)
            Text("Sumbu Y: $yAngle°", style = MaterialTheme.typography.headlineMedium)
        }
    }
}
