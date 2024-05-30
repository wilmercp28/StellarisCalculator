package com.example.stellariscalculator.Model


import androidx.lifecycle.ViewModel
import com.example.stellariscalculator.R
import java.util.Locale
import kotlin.math.abs

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

    fun convert(resource1:Resources, resource2:Resources, quantity: Int): Pair<String,String>{
        val value = (resource1.basePrice.toDouble() / resource2.basePrice.toDouble()) * quantity.toDouble()
        val conversion = if (value % 1 == 0.0) {
            // If the decimal part is zero, format as an integer
            String.format(Locale.ROOT, "%.0f", value)
        } else {
            // If there's a non-zero decimal part, format as a floating-point number with two decimal places
            String.format(Locale.ROOT, "%.2f", value)
        }
        val ratio = resource1.basePrice.toFloat() / resource2.basePrice.toFloat()
        val fraction = floatToFraction(ratio)
        val fractionInString = "${fraction.first} : ${fraction.second}"
        return Pair(conversion,fractionInString)
    }

    private fun floatToFraction(value: Float): Pair<Int, Int> {
        val tolerance = 1.0E-6
        var numerator = value
        var denominator = 1.0

        while (abs(numerator - Math.round(numerator)) > tolerance) {
            numerator *= 10
            denominator *= 10
        }

        val gcd = gcd(numerator.toInt(), denominator.toInt())
        return Pair(numerator.toInt() / gcd, denominator.toInt() / gcd)
    }
    private fun gcd(a: Int, b: Int): Int {
        if (b == 0) return a
        return gcd(b, a % b)
    }

}