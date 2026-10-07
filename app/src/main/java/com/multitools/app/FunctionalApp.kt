package com.multitools.app
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class T(val name:String,val icon:androidx.compose.ui.graphics.vector.ImageVector)
val allTools=listOf(T("Kalkulator",Icons.Default.Calculate),T("Senter",Icons.Default.FlashlightOn),T("Pengukur",Icons.Default.Straighten),T("Clipboard",Icons.Default.ContentPaste),T("Password",Icons.Default.Password),T("Konverter",Icons.Default.SwapHoriz),T("Perangkat",Icons.Default.Smartphone),T("Jaringan",Icons.Default.Wifi))

@Composable fun FunctionalApp(){
 var page by remember{mutableStateOf<String?>(null)}
 if(page!=null){ToolPage(page!!){page=null};return}
 var q by remember{mutableStateOf("")}
 val list=allTools.filter{it.name.contains(q,true)}
 Column(Modifier.fillMaxSize().padding(16.dp)){
  Text("MultiTools",style=MaterialTheme.typography.headlineMedium)
  Text("All tools are ready")
  OutlinedTextField(q,{q=it},Modifier.fillMaxWidth(),placeholder={Text("Search")})
  Spacer(Modifier.height(12.dp))
  LazyVerticalGrid(GridCells.Fixed(2),verticalArrangement=Arrangement.spacedBy(10.dp),horizontalArrangement=Arrangement.spacedBy(10.dp)){
   items(list){t->Card(Modifier.height(120.dp).clickable{page=t.name}){Column(Modifier.padding(16.dp)){Icon(t.icon,null);Spacer(Modifier.height(8.dp));Text(t.name)}}}
  }
 }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun ToolPage(name:String,back:()->Unit){
 Column(Modifier.fillMaxSize()){
  TopAppBar(title={Text(name)},navigationIcon={IconButton(onClick=back){Icon(Icons.Default.ArrowBack,"Back")}})
  Box(Modifier.fillMaxSize().padding(16.dp)){
   when(name){"Kalkulator"->CalcPage();"Senter"->TorchPage();"Pengukur"->LevelPage();"Clipboard"->ClipPage();"Password"->PassPage();"Konverter"->ConvertPage();"Perangkat"->DevicePage();"Jaringan"->NetworkPage()}
  }
 }
}