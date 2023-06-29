package com.elvirafatkhutdinova.cocktailguideapp.data.model

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromStringType(value: String) : Int {
        return value.toInt()
    }

    @TypeConverter
    fun toStringType(value: Int) : String {
        return value.toString()
    }
}