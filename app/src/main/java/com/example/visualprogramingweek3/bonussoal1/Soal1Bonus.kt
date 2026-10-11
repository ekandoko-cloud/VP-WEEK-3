package com.example.visualprogramingweek3.bonussoal1

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

val Oranye = Color(0xFFD96226)
val Coklat = Color(0xFF4A1D0E)
val Krem = Color(0xFFF9F4F1)
val Peach = Color(0xFFFBD9B8)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal1BonusPreview() {
    Soal1Bonus()
}

@Composable
fun Soal1Bonus() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var jumlah by remember { mutableIntStateOf(3) }
    var large by remember { mutableStateOf(false) }
    val hargaSatuan = if (large) 31000 else 25000
    val subtotal = hargaSatuan * jumlah
    val pajak = subtotal / 10
    val total = subtotal + pajak
    val subtotalText = "Rp " + "%,d".format(subtotal).replace(',', '.')
    val pajakText = "Rp " + "%,d".format(pajak).replace(',', '.')
    val totalText = "Rp " + "%,d".format(total).replace(',', '.')

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Krem)
            .statusBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Kopi Kenangan Senja", color = Coklat, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("☕", fontSize = 20.sp)
        }

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = cardColors(Krem),
            border = BorderStroke(1.dp, Peach)
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Coklat),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Coffee, contentDescription = null, tint = Peach, modifier = Modifier.size(64.dp))
                }
                Spacer(Modifier.height(10.dp))
                Text("Caramel Latte", color = Coklat, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("Espresso shot, steamed milk & caramel syrup", color = Coklat, fontSize = 12.sp, textAlign = TextAlign.Center)
                Spacer(Modifier.height(6.dp))
                Text("Rp 25.000 / cup", color = Oranye, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }

        Text("PILIHAN UKURAN:", color = Coklat, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (!large) Peach else Krem)
                    .border(if (!large) 2.dp else 1.dp, if (!large) Oranye else Peach, RoundedCornerShape(10.dp))
                    .clickable { large = false },
                contentAlignment = Alignment.Center
            ) {
                Text("Regular (+0)", color = if (!large) Oranye else Coklat, fontSize = 13.sp, fontWeight = FontWeight.Medium)
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (large) Peach else Krem)
                    .border(if (large) 2.dp else 1.dp, if (large) Oranye else Peach, RoundedCornerShape(10.dp))
                    .clickable { large = true },
                contentAlignment = Alignment.Center
            ) {
                Text("Large (+6k)", color = if (large) Oranye else Coklat, fontSize = 13.sp, fontWeight = FontWeight.Medium)
            }
        }

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = cardColors(Krem),
            border = BorderStroke(1.dp, Peach)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Jumlah Pesanan:", color = Coklat, fontWeight = FontWeight.Bold, fontSize = 14.sp)

                Row(horizontalArrangement = Arrangement.spacedBy(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Krem)
                            .border(1.dp, Peach, RoundedCornerShape(8.dp))
                            .clickable { if (jumlah > 1) jumlah-- },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("-", color = Coklat, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }

                    Text("$jumlah", color = Coklat, fontWeight = FontWeight.Bold, fontSize = 18.sp)

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Peach)
                            .border(1.dp, Peach, RoundedCornerShape(8.dp))
                            .clickable { jumlah++ },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("+", color = Oranye, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Peach.copy(alpha = 0.3f))
                .drawBehind {
                    drawRoundRect(
                        color = Oranye,
                        cornerRadius = CornerRadius(12.dp.toPx()),
                        style = Stroke(width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 12f)))
                    )
                }
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Subtotal:", color = Coklat, fontSize = 13.sp)
                Text(subtotalText, color = Coklat, fontSize = 13.sp)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Pajak Resto (10%):", color = Coklat, fontSize = 13.sp)
                Text(pajakText, color = Coklat, fontSize = 13.sp)
            }
            HorizontalDivider(color = Peach)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total Tagihan:", color = Coklat, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Text(totalText, color = Oranye, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(Modifier.weight(1f))
        SnackbarHost(snackbarHostState) {
            Snackbar(it, containerColor = Coklat, contentColor = Krem)
        }

        Button(
            onClick = {
                val ukuran = if (large) "Large" else "Regular"
                scope.launch {
                    snackbarHostState.showSnackbar("$jumlah Caramel Latte ($ukuran) ditambahkan ke keranjang. Total $totalText")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Oranye, contentColor = Krem)
        ) {
            Text("TAMBAH KE KERANJANG", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        }
    }
}