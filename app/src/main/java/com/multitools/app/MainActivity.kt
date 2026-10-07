package com.multitools.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.FlashlightOn
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.multitools.app.ui.theme.MultiToolsTheme

data class Tool(val name: String, val description: String, val icon: ImageVector)

private val tools = listOf(
    Tool("Kalkulator", "Hitung cepat", Icons.Default.Calculate),
    Tool("Senter", "Lampu flash", Icons.Default.FlashlightOn),
    Tool("Pengukur", "Ukur sederhana", Icons.Default.Straighten),
    Tool("Teks & Clipboard", "Alat teks", Icons.Default.ContentPaste),
    Tool("Password", "Generator aman", Icons.Default.Password),
    Tool("Konverter", "Satuan & ukuran", Icons.Default.SwapHoriz),
    Tool("Info Perangkat", "Detail HP", Icons.Default.Smartphone),
    Tool("Info Jaringan", "Koneksi jaringan", Icons.Default.Wifi)
)

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MultiToolsTheme { MultiToolsApp() } }
    }
}

@Composable
fun MultiToolsApp() {
    var query by remember { mutableStateOf("") }
    val filtered = tools.filter {
        it.name.contains(query, ignoreCase = true) ||
            it.description.contains(query, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            modifier = Modifier.size(42.dp),
                            shape = RoundedCornerShape(13.dp),
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Construction, null)
                            }
                        }
                        Spacer(Modifier.size(10.dp))
                        Column {
                            Text("MultiTools", fontWeight = FontWeight.Bold)
                            Text(
                                "All your tools in one app",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {}) { Icon(Icons.Default.Settings, "Pengaturan") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                leadingIcon = { Icon(Icons.Default.Search, null) },
                placeholder = { Text("Cari tools...") },
                shape = RoundedCornerShape(15.dp)
            )

            Text(
                "⭐ FAVORIT",
                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ToolCard(tools[0], Modifier.weight(1f))
                ToolCard(tools[4], Modifier.weight(1f))
            }

            Text(
                "🧰 SEMUA TOOLS",
                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filtered) { tool -> ToolCard(tool) }
            }
        }
    }
}

@Composable
private fun ToolCard(tool: Tool, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(118.dp)
            .clickable { },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                tool.icon,
                contentDescription = tool.name,
                modifier = Modifier.size(28.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(10.dp))
            Text(tool.name, fontWeight = FontWeight.Bold)
            Text(
                tool.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
