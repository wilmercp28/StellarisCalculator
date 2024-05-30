package com.example.stellariscalculator.Composable_UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stellariscalculator.R



@Composable
fun HomeScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val resourcesIconId = painterResource(id = R.drawable.dark_matter)
        Spacer(modifier = Modifier.size(50.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Selectable_icon(navController = navController, icon = resourcesIconId,"Resources Calculator", route = "ResourcesCalculator")
        }

    }

}

@Composable
fun Selectable_icon(
    navController: NavController,
    icon: Painter,
    title: String,
    route: String
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = { navController.navigate(route) },
            modifier = Modifier
                .size(100.dp)

        ) {
            Image(
                painter = icon,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxSize()
            )

        }
        Text(
            text = title,
            fontSize = 10.sp
        )
    }

}