package com.example.data.data_source.remote.typeconverter

import androidx.room.TypeConverter
import com.example.data.data_source.remote.responses.Geometry
import com.google.gson.Gson

class GeometryTypeConverter {

    @TypeConverter
    fun convertStringToSourceModel(data: String?): Geometry? {
        data?.let {
            return Gson().fromJson(it, Geometry::class.java)
        }
        return  null
    }

    @TypeConverter
    fun convertSourceModelToString(data: Geometry?): String? {
        return Gson().toJson(data)
    }
}