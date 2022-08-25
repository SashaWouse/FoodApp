package com.example.foodapp.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class MealTypeConverter {

    @TypeConverter
    fun fromToString(attribute: Any?) : String{
        if(attribute == null)
            return ""
        return attribute as String
    }
    @TypeConverter
    fun fromStringToAny(attribute: String?):Any{
        if(attribute == null)
            return ""
        return attribute
    }
}