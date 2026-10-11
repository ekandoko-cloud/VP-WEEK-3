package com.example.visualprogramingweek3.soal2bonus

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

val Gelap = Color(0xFF0F1426)
val Biru = Color(0xFF5DB8F5)
val BiruTua = Color(0xFF1E3A8A)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal2BonusPreview() {
    Soal2BonusView()
}

@Composable
fun Soal2BonusView() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var spot by remember { mutableStateOf("Fjellheisen Cable Car") }
    var pengalaman by remember { mutableStateOf("Pemandangan langit malam hijau aurora spektakuler dari puncak gunung...") }
    var catatan by remember { mutableStateOf("") }
    var kepuasan by remember { mutableStateOf("Luar Biasa ★") }

    MaterialTheme(colorScheme = darkColorScheme(primary = Biru, onPrimary = Gelap)) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gelap)
                    .statusBarsPadding()
                    .imePadding()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Brush.horizontalGradient(listOf(BiruTua, Biru)), RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Text("LOG PERJALANAN", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Text("Tromsø, Norway ❄", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("Ekspedisi Aurora Borealis", color = Color.White, fontSize = 12.sp)
                }

                OutlinedTextField(
                    value = spot,
                    onValueChange = { spot = it },
                    label = { Text("SPOT WISATA FAVORIT") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = pengalaman,
                    onValueChange = { pengalaman = it },
                    label = { Text("APA YANG PALING KAMU NIKMATI?") },
                    minLines = 3,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = catatan,
                    onValueChange = { catatan = it },
                    label = { Text("CATATAN TAMBAHAN / PERLENGKAPAN") },
                    placeholder = { Text("(Ketik perlengkapan ekstra di sini...)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Text("TINGKAT KEPUASAN:", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Biasa", "Seru", "Luar Biasa ★").forEach { pilihan ->
                        val dipilih = kepuasan == pilihan
                        Button(
                            onClick = { kepuasan = pilihan },
                            border = BorderStroke(1.dp, Color.Gray),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (dipilih) Biru else Gelap,
                                contentColor = if (dipilih) Gelap else Color.White
                            )
                        ) {
                            Text(pilihan, fontSize = 12.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Row {
                    Text("Status: ", color = Color.Gray, fontSize = 12.sp)
                    Text("Draft Tersimpan", color = Biru, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }

            FloatingActionButton(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Jurnal tersimpan! Spot favorit: ${spot.ifBlank { "(belum diisi)" }}")
                    }
                },
                containerColor = Biru,
                contentColor = Gelap,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .navigationBarsPadding()
                    .padding(16.dp)
            ) {
                Text("+", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .navigationBarsPadding()
                    .padding(bottom = 88.dp)
            )
        }
    }
}