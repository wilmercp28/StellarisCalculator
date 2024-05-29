package com.example.stellariscalculator.Composable_UI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.stellariscalculator.Model.ResourcesCalculatorViewModel

@Composable
fun ResourcesCalculator(
    viewModel: ResourcesCalculatorViewModel = ResourcesCalculatorViewModel(),
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var expanded by remember { mutableStateOf(true) }
        var selectedResource by remember { mutableStateOf(viewModel.resourcesList[0]) }
        Button(onClick = { expanded = !expanded }) {
            Text(text = selectedResource.Name)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false},
            modifier = Modifier
            ) {
            viewModel.resourcesList.forEach { resource ->
                DropdownMenuItem(text = { Text(text = resource.Name) }, onClick = {
                    expanded = false
                    selectedResource = resource
                })
            }

        }
    }

}