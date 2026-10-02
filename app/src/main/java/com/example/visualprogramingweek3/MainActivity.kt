package com.example.visualprogramingweek3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.visualprogramingweek3.Soal1.Soal1
import com.example.visualprogramingweek3.bonussoal1.Soal1Bonus
import com.example.visualprogramingweek3.soal2.Soal2View
import com.example.visualprogramingweek3.soal2bonus.Soal2BonusView
import com.example.visualprogramingweek3.soal3.Soal3View
import com.example.visualprogramingweek3.soal4.Soal4View
import com.example.visualprogramingweek3.ui.theme.VISUALPROGRAMINGWEEK3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VISUALPROGRAMINGWEEK3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Soal1(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}