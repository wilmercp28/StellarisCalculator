package com.example.stellariscalculator.Model


import androidx.lifecycle.ViewModel
import com.example.stellariscalculator.R

class ResourcesCalculatorViewModel: ViewModel() {

    var resourcesList = listOf<Resources>(
        Resources("Energy",R.drawable.energy,1),
        Resources("Minerals",R.drawable.minerals,1),
        Resources("Food",R.drawable.food,1),
        Resources("Consumer Goods",R.drawable.consumer_goods,2),
        Resources("Alloy",R.drawable.alloys,4),
        Resources("Rare Crystals",R.drawable.rare_crystals,10),
        Resources("Volatile Motes",R.drawable.volatile_motes,10),
        Resources("Exotics Gases",R.drawable.exotic_gases,10),
        Resources("Zro", R.drawable.zro,20),
        Resources("Living Metal",R.drawable.living_metal,20),
        Resources("Dark Matter",R.drawable.dark_matter,20),
        Resources("Trade Value",R.drawable.trade_value,1)
    )

    fun convert(resource1:Resources,resource2:Resources,quantity:Int): Float{
        return ((resource1.basePrice / resource2.basePrice) * quantity.toFloat())
    }

}