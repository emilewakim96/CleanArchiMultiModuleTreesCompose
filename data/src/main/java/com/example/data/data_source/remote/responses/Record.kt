package com.example.data.data_source.remote.responses

import com.example.common.entity.Tree

data class Record(
    val datasetid: String?,
    val fields: Fields?,
    val geometry: Geometry?,
    val record_timestamp: String?,
    val recordid: String
)


fun mapRecordToTree(record: Record): Tree {
    return Tree(
        id = record.recordid,
        adresse = record.fields?.adresse ?: "",
        circonferenceencm = record.fields?.circonferenceencm ?: 0,
        espece = record.fields?.espece ?: "",
        hauteurenm = record.fields?.hauteurenm ?: 0)
}