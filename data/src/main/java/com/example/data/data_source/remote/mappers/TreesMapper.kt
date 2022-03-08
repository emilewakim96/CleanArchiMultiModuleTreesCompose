package com.example.data.data_source.remote.mappers

import com.example.data.data_source.remote.responses.Record
import com.example.data.data_source.remote.responses.Tree

fun Record.mapRecordToTree(): Tree {
    return Tree(
        id = this.recordid,
        adresse = this.fields?.adresse ?: "",
        circonferenceencm = this.fields?.circonferenceencm ?: 0,
        espece = this.fields?.espece ?: "",
        hauteurenm = this.fields?.hauteurenm ?: 0)
}
