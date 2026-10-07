package com.multitools.app
import android.content.Context
import android.hardware.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
@Composable fun LevelPage(){val c=LocalContext.current;var x by remember{mutableStateOf(0f)};var y by remember{mutableStateOf(0f)};DisposableEffect(Unit){val sm=c.getSystemService(Context.SENSOR_SERVICE) as SensorManager;val s=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);val l=object:SensorEventListener{override fun onSensorChanged(e:SensorEvent){x=e.values[0];y=e.values[1]};override fun onAccuracyChanged(s:Sensor?,a:Int){}};if(s!=null)sm.registerListener(l,s,SensorManager.SENSOR_DELAY_UI);onDispose{sm.unregisterListener(l)}};val xx=((kotlin.math.abs(x)/9.81f)*90).coerceIn(0f,90f).toInt();val yy=((kotlin.math.abs(y)/9.81f)*90).coerceIn(0f,90f).toInt();Column(Modifier.fillMaxSize(),horizontalAlignment=Alignment.CenterHorizontally,verticalArrangement=Arrangement.Center){Text("X "+xx+" deg",style=MaterialTheme.typography.headlineMedium);Text("Y "+yy+" deg",style=MaterialTheme.typography.headlineMedium)}}