package com.example.appwithfragments.models

import java.io.Serializable

data class TourPlace(
    val id:Int,
    val img:String,
    val name:String,
    val description:String,
    val rating:Double,
    val temperature:String,
    val location: String,
    val recommendedPlaces: String,
    val description_short: String
    ): Serializable
