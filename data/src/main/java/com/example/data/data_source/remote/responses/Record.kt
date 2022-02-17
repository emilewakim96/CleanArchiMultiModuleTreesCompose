package com.example.data.data_source.remote.responses

import com.example.domain.models.Tree

data class Record(
    val datasetid: String?,
    val fields: Fields?,
    val geometry: Geometry?,
    val record_timestamp: String?,
    val recordid: String
)