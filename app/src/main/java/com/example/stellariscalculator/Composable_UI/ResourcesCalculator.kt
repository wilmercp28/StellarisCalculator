package com.example.stellariscalculator.Composable_UI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
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
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var selectedResource1 by remember { mutableStateOf(viewModel.resourcesList[0]) }
        var selectedResource2 by remember { mutableStateOf(viewModel.resourcesList[0]) }
        var quantity by remember { mutableStateOf("0") }
        var resultPair by remember { mutableStateOf(viewModel.convert(resource1 = selectedResource1, resource2 = selectedResource2,quantity.toInt())) }
        Text(text = "Resources Converter", fontSize = 30.sp, textAlign = TextAlign.Center)
        TextLabel(label = resultPair.first,"Result", textSize = 20.sp, height = 60.dp)
        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "")
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DropMenu(viewModel = viewModel,selectedResource1, onResourceSelected = {
                selectedResource1 = it
                resultPair = viewModel.convert(resource1 = selectedResource1, resource2 = selectedResource2, quantity.toInt())
            })
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "")
            Text(text = resultPair.second)
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            DropMenu(viewModel = viewModel,selectedResource2, onResourceSelected = {
                selectedResource2 = it
                resultPair = viewModel.convert(resource1 = selectedResource1, resource2 = selectedResource2, quantity.toInt())
            })
        }
        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "")
        TextLabel(label = quantity,"Quantity", textSize = 20.sp,60.dp)
        KeyBoardComposable(size = 70.dp) {
            if (it.isDigitsOnly() && quantity.length < 9){
                if (quantity.first() == '0' && it != "0"){quantity = it} else {
                    quantity += it
                }
            } else if (it == "<"&& quantity.isNotBlank()){
                quantity  = quantity.substring(0,quantity.length - 1)
                if (quantity == "") {quantity = "0"}
            } else if (it == "r"){
                quantity = "0"
            }
            val conversionResult = viewModel.convert(resource1 = selectedResource1, resource2 = selectedResource2,quantity.toInt())
            resultPair = conversionResult
        }
    }
}

@Composable
fun TextLabel(label:String,title:String, textSize: TextUnit,height:Dp){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(height)
            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = title, fontSize = textSize)
        Text(text = label, fontSize = textSize)
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
                                        .size(30.dp),
                                    tint = Color.Unspecified
                                )
                            }
                        )
                    }
                }
            }
}