package com.example.visualprogramingweek3.soal2

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.visualprogramingweek3.R
import kotlin.math.roundToInt

@Composable
fun Soal2View(modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    var coinCount by remember { mutableStateOf(0) }
    var coinsPerTap by remember { mutableStateOf(1) }
    var upgradeCost by remember { mutableStateOf(10) }
    val nextCoinsPerTap = (coinsPerTap * 1.5).roundToInt()
    val canUpgrade = coinCount >= upgradeCost
    val remainingCoins = maxOf(0, upgradeCost - coinCount)

    val catImageRes = if (isPressed) {
        R.drawable.catopen
    } else {
        R.drawable.catclose
    }
    val catSoundText = if (isPressed) {
        "Meow!"
    } else {
        "Purr~"
    }
    val upgradeBtnText = if (canUpgrade) {
        "Pay for $upgradeCost coins"
    } else {
        "Find $remainingCoins more coins"
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 40.dp)
                    .size(170.dp),
                shape = RoundedCornerShape(16.dp),
                colors = cardColors(Color.White.copy(alpha = 0.3f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Your Coins",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                    Text(
                        "$coinCount",
                        modifier = Modifier.padding(5.dp),
                        color = Color(0xFF00FF88),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "$coinsPerTap coins per tap",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }

            Text(
                "Tap the cat",
                modifier = Modifier.padding(12.dp),
                fontSize = 25.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Image(
                painter = painterResource(id = catImageRes),
                contentDescription = "Tap the Cat",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(
                        interactionSource = interactionSource,
                    ) {
                        coinCount += coinsPerTap
                    })

            Text(
                catSoundText,
                modifier = Modifier.padding(12.dp),
                fontSize = 20.sp,
                color = Color.White
            )

            Card(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .padding(top = 10.dp, bottom = 40.dp)
                    .fillMaxWidth()
                    .height(170.dp),
                shape = RoundedCornerShape(16.dp),
                colors = cardColors(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Give me your coin",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Text("Next upgrade: +$nextCoinsPerTap coins per tap", fontSize = 16.sp)

                    Button(
                        onClick = {
                            coinCount -= upgradeCost
                            upgradeCost *= 2
                            coinsPerTap = nextCoinsPerTap
                        },
                        enabled = canUpgrade,
                        modifier = Modifier
                            .width(320.dp)
                            .height(60.dp)
                            .padding(5.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF089C14))
                    ) {
                        Text(upgradeBtnText, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal2Preview() {
    Soal2View()
}