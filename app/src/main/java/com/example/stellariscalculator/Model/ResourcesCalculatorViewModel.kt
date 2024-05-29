package com.example.stellariscalculator.Model

import androidx.lifecycle.ViewModel

class ResourcesCalculatorViewModel: ViewModel() {

    val mineralsBasePrice = 1
    val foodBasePrice = 1
    val alloyBasePrice = 4
    val consumerBasePrice = 2
    val crystalsBasePrice = 10
    val motesBasePrice = 10
    val gasesBasePrice = 10
    val darkMatterBasePrice = 20
    val livingMetalBasePrice = 20
    val zroBasePrice = 20

    var resourcesList = listOf<Resources>(
        Resources("Energy",1),
        Resources("Minerals",1),
        Resources("Food",1),
        Resources("Consumer Goods",2),
        Resources("Alloy",4),
        Resources("Rare Crystals",10),
        Resources("Volatile Motes",10),
        Resources("Exotics Gases",10),
        Resources("Zro",20),
        Resources("Living Metal",20),
        Resources("Dark Matter",20),
        Resources("Trade Value",1)
    )

    fun convert(resource1:Resources,resource2:Resources,quantity:Int): Float{
        return ((resource1.basePrice / resource2.basePrice) * quantity.toFloat())
    }












}