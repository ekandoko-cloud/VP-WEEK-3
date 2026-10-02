package com.example.visualprogramingweek3.Soal1

import com.example.visualprogramingweek3.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random

enum class StatusGame(
    val warna: Color,
    val ikon: ImageVector? = null,
    val teksAtas: String = "",
    val teksTengah: String = "",
    val teksBawah: String = ""
) {
    AWAL(
        warna = Color(0xFF71CEDD),
        ikon = Icons.Default.FlashOn,
        teksAtas = "Reaction",
        teksTengah = "Test",
        teksBawah = "Click to Start"
    ),
    MENUNGGU(
        warna = Color(0xFFD9D9D9),
        ikon = Icons.Default.Warning,
        teksAtas = "Get Ready",
        teksTengah = "Wait for green light...",
        teksBawah = "DON'T CLICK YET!"
    ),
    SIAP(
        warna = Color(0xFF4CAF50),
        ikon = Icons.AutoMirrored.Filled.DirectionsWalk,
        teksAtas = "GO!",
        teksTengah = "CLICK NOW!",
        teksBawah = "TAP AS FAST AS YOU CAN!"
    ),
    TRIAL_SELESAI(
        warna = Color(0xFF4CAF50),
        ikon = Icons.Default.CheckCircle
    ),
    GAGAL(
        warna = Color(0xFFFA4444),
        ikon = Icons.Default.ThumbDown,
        teksAtas = "FAIL!",
        teksTengah = "You clicked too early, TRY TO READ THE RULE BRO",
        teksBawah = "TRY AGAIN"
    ),
    FINAL(
        warna = Color.Unspecified
    )
}

enum class KategoriHasil(val pesan: String, val warna: Color, val gambar: Int) {
    CEPAT("DANG YOU ARE SO FAST BRO!", Color(0xFF00E676), R.drawable.salute),
    BAGUS("YOUR REFLEX IS GOOD", Color(0xFF2196F3), R.drawable.thumb_up),
    BIASA("MEH LIKE OTHER PERSON", Color(0xFFFF9800), R.drawable.meh),
    LAMBAT("YOU LIKE A SNAIL BRO", Color(0xFFFF5722), R.drawable.thumb_down)
}

@Composable
fun Soal1(modifier: Modifier = Modifier) {
    var state by remember { mutableStateOf(StatusGame.AWAL) }
    var waktuMulai by remember { mutableStateOf(0L) }
    val daftarHasil = remember { mutableStateListOf<Int?>() }

    LaunchedEffect(state) {
        if (state == StatusGame.MENUNGGU) {
            delay(Random.nextLong(500, 4500))
            waktuMulai = System.currentTimeMillis()
            state = StatusGame.SIAP
        }
    }

    val hasilValid = daftarHasil.filterNotNull()
    val rataRata = if (hasilValid.isNotEmpty()) hasilValid.average().toInt() else 9999
    val teksRataRata = if (hasilValid.isNotEmpty()) "${rataRata}ms" else "-"

    val kategori = when {
        rataRata < 180 -> KategoriHasil.CEPAT
        rataRata < 280 -> KategoriHasil.BAGUS
        rataRata < 450 -> KategoriHasil.BIASA
        else -> KategoriHasil.LAMBAT
    }

    val warnaBackground = if (state == StatusGame.FINAL) kategori.warna else state.warna

    val teksAtas = when (state) {
        StatusGame.TRIAL_SELESAI -> "Trial ${daftarHasil.size} Complete!"
        StatusGame.FINAL -> kategori.pesan
        else -> state.teksAtas
    }

    val teksTengah = when (state) {
        StatusGame.TRIAL_SELESAI -> "Time: ${daftarHasil.lastOrNull()}ms"
        StatusGame.FINAL -> "Average: $teksRataRata"
        else -> state.teksTengah
    }

    val teksBawah = when (state) {
        StatusGame.TRIAL_SELESAI -> "Continue to Trial ${daftarHasil.size + 1}"
        StatusGame.FINAL -> "Click to Start New Test"
        else -> state.teksBawah
    }

    val skor1 =
        if (daftarHasil.isNotEmpty() && daftarHasil[0] != null) {
            "${daftarHasil[0]}ms"
        } else {
            "-"
        }

    val skor2 = if (daftarHasil.size > 1 && daftarHasil[1] != null) {
        "${daftarHasil[1]}ms"
    } else {
        "-"
    }
    val skor3 = if (daftarHasil.size > 2 && daftarHasil[2] != null) {
        "${daftarHasil[2]}ms"
    } else {
        "-"
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(warnaBackground)
            .clickable {
                when (state) {
                    StatusGame.AWAL -> {
                        state = StatusGame.MENUNGGU
                    }

                    StatusGame.MENUNGGU -> {
                        if (daftarHasil.isNotEmpty()) {
                            daftarHasil.add(null)
                        }
                        state = StatusGame.GAGAL
                    }

                    StatusGame.SIAP -> {
                        val selisihWaktu = (System.currentTimeMillis() - waktuMulai).toInt()
                        daftarHasil.add(selisihWaktu)
                        state =
                            if (daftarHasil.size == 3) StatusGame.FINAL else StatusGame.TRIAL_SELESAI
                    }

                    StatusGame.TRIAL_SELESAI -> {
                        state = StatusGame.MENUNGGU
                    }

                    StatusGame.GAGAL -> {
                        state = when (daftarHasil.size) {
                            3 -> StatusGame.FINAL
                            2 -> StatusGame.MENUNGGU
                            else -> StatusGame.AWAL
                        }
                    }

                    StatusGame.FINAL -> {
                        daftarHasil.clear()
                        state = StatusGame.AWAL
                    }
                }
            }
    ) {
        Text(
            text = teksAtas,
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))

        if (state == StatusGame.FINAL) {
            Image(
                painter = painterResource(id = kategori.gambar),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
        } else {
            state.ikon?.let { iconVector ->
                Icon(
                    imageVector = iconVector,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(120.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = teksTengah,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = teksBawah,
            color = Color.White,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (daftarHasil.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Trial Results",
                    color = Color(0xFF1976D2),
                    fontWeight = FontWeight.Bold
                )

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("1", color = Color(0xFF4CAF50))
                        Text(skor1, color = Color.Black)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("2", color = Color(0xFF4CAF50))
                        Text(skor2, color = Color.Black)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("3", color = Color(0xFF4CAF50))
                        Text(skor3, color = Color.Black)
                    }
                }

                if (state == StatusGame.FINAL) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Average Score", color = Color(0xFF1976D2))
                    Text(
                        text = teksRataRata,
                        color = Color(0xFFE65100),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal1Preview() {
    Soal1()
}