package com.example.data.data_source.remote.typeconverter

import androidx.room.TypeConverter
import com.example.data.data_source.remote.responses.Fields
import com.google.gson.Gson

class FieldsTypeConverter {

    @TypeConverter
    fun convertStringToSourceModel(data: String?): Fields? {
        data?.let {
            return Gson().fromJson(it, Fields::class.java)
        }
        return  null
    }

    @TypeConverter
    fun convertSourceModelToString(data: Fields?): String? {
        return Gson().toJson(data)
    }
}