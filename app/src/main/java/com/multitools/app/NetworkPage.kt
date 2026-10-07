package com.multitools.app
import android.content.Context
import android.net.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
@Composable fun NetworkPage(){val c=LocalContext.current;val m=c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager;val n=m.activeNetwork;val z=n?.let{m.getNetworkCapabilities(it)};val t=when{z?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)==true->"WiFi";z?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)==true->"Cellular";z?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)==true->"Ethernet";else->"Offline"};Column{Text("Connection: "+t,style=MaterialTheme.typography.headlineSmall);Text("Internet: "+if(z?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)==true)"Available" else "Unavailable")}}