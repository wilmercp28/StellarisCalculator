package com.example.stellariscalculator.Composable_UI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stellariscalculator.Model.Resources
import com.example.stellariscalculator.Model.ResourcesCalculatorViewModel

@OptIn(ExperimentalMaterial3Api::class)
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
        var selectedResource1 by remember { mutableStateOf(viewModel.resourcesList[0]) }
        var selectedResource2 by remember { mutableStateOf(viewModel.resourcesList[0]) }
        var result by remember { mutableStateOf("") }
        Text(text = result)
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DropMenu(viewModel = viewModel,selectedResource1, onResourceSelected = {selectedResource1 = it})
            Text(text = "1:1")
            DropMenu(viewModel = viewModel,selectedResource2, onResourceSelected = {selectedResource2 = it})
        }
        var quantity by remember { mutableStateOf("") }
        TextField(value = quantity, onValueChange = {
            quantity = it
            if (quantity.isNotBlank()) {
                result =
                    viewModel.convert(selectedResource1, selectedResource2, it.toInt()).toString()
            }
        })

    }
}

@Composable
fun DropMenu(
    viewModel: ResourcesCalculatorViewModel,
    selectedResource: Resources,
    onResourceSelected: (Resources) -> Unit

){
    var expanded by remember { mutableStateOf(false) }
        IconButton(
            onClick = { expanded = !expanded },
            modifier = Modifier
                .size(100.dp)
        ) {
                Icon(
                    painter = painterResource(id = selectedResource.icon),
                    contentDescription = selectedResource.Name,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(400.dp)
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                )
                {
                    viewModel.resourcesList.forEach { resource ->
                        DropdownMenuItem(
                            text = { Text(text = resource.Name) }, onClick = {
                                expanded = false
                                onResourceSelected(resource)
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = resource.icon),
                                    contentDescription = resource.Name,
                                    modifier = Modifier
                                        .size(50.dp),
                                    tint = Color.Unspecified
                                )
                            }
                        )
                    }
                }
            }
}