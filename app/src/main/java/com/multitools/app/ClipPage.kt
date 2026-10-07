package com.multitools.app
import android.content.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
@Composable fun ClipPage(){val c=LocalContext.current;val m=c.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager;var x by remember{mutableStateOf("")};Column{OutlinedTextField(x,{x=it},Modifier.fillMaxWidth().height(180.dp),label={Text("Text")});Row{Button(onClick={m.setPrimaryClip(ClipData.newPlainText("MultiTools",x))}){Text("COPY")};Button(onClick={x=m.primaryClip?.getItemAt(0)?.coerceToText(c)?.toString()?:""}){Text("PASTE")}}}}