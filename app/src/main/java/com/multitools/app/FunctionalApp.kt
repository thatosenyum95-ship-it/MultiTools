package com.multitools.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class T(
    val name: String,
    val subtitle: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val accent: Color
)

val allTools = listOf(
    T("Kalkulator", "Hitung cepat & praktis", Icons.Default.Calculate, Color(0xFF315EF6)),
    T("Senter", "Terangi sekelilingmu", Icons.Default.FlashlightOn, Color(0xFFFFA000)),
    T("Pengukur", "Cek kemiringan", Icons.Default.Straighten, Color(0xFF00A889)),
    T("Clipboard", "Kelola teks cepat", Icons.Default.ContentPaste, Color(0xFFE64980)),
    T("Password", "Buat sandi kuat", Icons.Default.Password, Color(0xFF7B4DDB)),
    T("Konverter", "Ubah satuan", Icons.Default.SwapHoriz, Color(0xFF0097A7)),
    T("Perangkat", "Info ponsel", Icons.Default.Smartphone, Color(0xFF5167D8)),
    T("Jaringan", "Cek koneksi", Icons.Default.Wifi, Color(0xFF00897B))
)

@Composable
fun FunctionalApp() {
    var page by remember { mutableStateOf<String?>(null) }
    if (page != null) {
        ToolPage(page!!) { page = null }
        return
    }

    var q by remember { mutableStateOf("") }
    var favoritesOnly by remember { mutableStateOf(false) }
    var favorites by remember { mutableStateOf(setOf<String>()) }

    val list = allTools.filter {
        it.name.contains(q, true) &&
            (!favoritesOnly || favorites.contains(it.name))
    }

    Scaffold(containerColor = MaterialTheme.colorScheme.background) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            HomeHeader()

            Column(Modifier.padding(horizontal = 18.dp)) {
                Spacer(Modifier.height(14.dp))

                OutlinedTextField(
                    value = q,
                    onValueChange = { q = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(18.dp),
                    leadingIcon = { Icon(Icons.Default.Search, null) },
                    trailingIcon = {
                        if (q.isNotEmpty()) {
                            IconButton(onClick = { q = "" }) {
                                Icon(Icons.Default.Close, "Hapus pencarian")
                            }
                        }
                    },
                    placeholder = { Text("Cari alat...") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                    )
                )

                Spacer(Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = !favoritesOnly,
                        onClick = { favoritesOnly = false },
                        label = { Text("Semua") },
                        leadingIcon = { Icon(Icons.Default.GridView, null, Modifier.size(18.dp)) }
                    )
                    FilterChip(
                        selected = favoritesOnly,
                        onClick = { favoritesOnly = true },
                        label = { Text("Favorit") },
                        leadingIcon = { Icon(Icons.Default.Star, null, Modifier.size(18.dp)) }
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        "${list.size} alat",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(Modifier.height(10.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 22.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(list, key = { it.name }) { tool ->
                        ToolCard(
                            tool = tool,
                            favorite = favorites.contains(tool.name),
                            onFavorite = {
                                favorites = if (favorites.contains(tool.name)) {
                                    favorites - tool.name
                                } else {
                                    favorites + tool.name
                                }
                            },
                            onClick = { page = tool.name }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 14.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFF172B72),
                        Color(0xFF315EF6),
                        Color(0xFF6D4AFF)
                    )
                )
            )
            .padding(22.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White.copy(alpha = 0.16f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            Icons.Default.Build,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(27.dp)
                        )
                    }
                Spacer(Modifier.width(14.dp))
                Column {
                    Text(
                        "MultiTools",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        "Semua alat penting, satu aplikasi.",
                        color = Color.White.copy(alpha = 0.82f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            Surface(
                shape = RoundedCornerShape(14.dp),
                color = Color.White.copy(alpha = 0.12f)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 13.dp, vertical = 9.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.AutoAwesome, null, tint = Color.White, modifier = Modifier.size(17.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Cepat • Ringan • Privat",
                        color = Color.White,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Composable
private fun ToolCard(
    tool: T,
    favorite: Boolean,
    onFavorite: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(166.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .size(92.dp)
                    .offset(x = 58.dp, y = (-24).dp)
                    .clip(RoundedCornerShape(50))
                    .background(tool.accent.copy(alpha = 0.08f))
            )

            Column(
                modifier = Modifier.fillMaxSize().padding(15.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Surface(
                        modifier = Modifier.size(50.dp),
                        shape = RoundedCornerShape(16.dp),
                        color = tool.accent.copy(alpha = 0.13f)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                tool.icon,
                                contentDescription = tool.name,
                                tint = tool.accent,
                                modifier = Modifier.size(26.dp)
                            )
                        }
                    }

                    IconButton(onClick = onFavorite, modifier = Modifier.size(34.dp)) {
                        Icon(
                            if (favorite) Icons.Default.Star else Icons.Default.StarBorder,
                            contentDescription = if (favorite) "Hapus favorit" else "Tambah favorit",
                            tint = if (favorite) Color(0xFFFFB300) else MaterialTheme.colorScheme.outline
                        )
                    }
                }

                Column {
                    Text(
                        tool.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        tool.subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolPage(name: String, back: () -> Unit) {
    val tool = allTools.firstOrNull { it.name == name }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(name, fontWeight = FontWeight.Bold)
                        Text(
                            tool?.subtitle.orEmpty(),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = back) {
                        Icon(Icons.Default.ArrowBack, "Kembali")
                    }
                },
                actions = {
                    if (tool != null) {
                        Icon(
                            tool.icon,
                            contentDescription = null,
                            tint = tool.accent,
                            modifier = Modifier.padding(end = 16.dp)
                        )
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            when (name) {
                "Kalkulator" -> CalcPage()
                "Senter" -> TorchPage()
                "Pengukur" -> LevelPage()
                "Clipboard" -> ClipPage()
                "Password" -> PassPage()
                "Konverter" -> ConvertPage()
                "Perangkat" -> DevicePage()
                "Jaringan" -> NetworkPage()
            }
        }
    }
}
