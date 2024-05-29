package com.example.stellariscalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import com.example.stellariscalculator.ui.theme.StellarisCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StellarisCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = androidx.navigation.compose.rememberNavController()
                   androidx.navigation.compose.NavHost(
                       navController = navController,
                       startDestination = "home_screen"
                   ){
                       composable(route = "home_screen"){
                           com.example.stellariscalculator.Composable_UI.HomeScreen(navController = navController)
                       }
                       composable(route = "ResourcesCalculator"){
                           com.example.stellariscalculator.Composable_UI.ResourcesCalculator(navController = navController)
                       }
                   }

                }
            }
        }
    }
}