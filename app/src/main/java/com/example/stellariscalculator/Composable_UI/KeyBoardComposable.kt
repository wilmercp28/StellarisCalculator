package com.example.stellariscalculator.Composable_UI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun KeyBoardComposable(
    size: Dp,
    onKeyPress: (String) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        for (i in 0 until 3){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (e in 0 until 3) {
                    val number = (i * 3 + e + 1).toString()
                    Key(size = size, label = number, onKeyPress = {onKeyPress(it)})

                }
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Key(size = size,label = "0", onKeyPress = {onKeyPress(it)})
            Key(size = size,label = "r", onKeyPress = {onKeyPress(it)})
            Key(size = size,label = "<", onKeyPress = {onKeyPress(it)})
        }
    }
}

@Composable
fun Key(
    size: Dp,
    label:String,
    onKeyPress: (String) -> Unit
){
    val configuration = LocalConfiguration.current
    val screenWidth = (configuration.screenWidthDp / 3) - 20
    Button(
        onClick = { onKeyPress(label) },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .width(screenWidth.dp)
            .padding(1.dp)
            .height(size)
    ) {
        Text(
            text = label,
            fontSize = (size.value / 2).sp
        )
    }
}