package com.example.data.data_source.remote.mappers

import com.example.data.data_source.remote.responses.Record
import com.example.data.data_source.remote.responses.Tree
import com.example.domain.entities.TreeEntity

fun Record.mapRecordToTree(): Tree {
    return Tree(
        id = this.recordid,
        adresse = this.fields?.adresse ?: "",
        circonferenceencm = this.fields?.circonferenceencm ?: 0,
        espece = this.fields?.espece ?: "",
        hauteurenm = this.fields?.hauteurenm ?: 0)
}

fun Record.mapRecordToTreeEntity(): TreeEntity {
    return TreeEntity(
        id = this.recordid,
        adresse = this.fields?.adresse ?: "",
        circonferenceencm = this.fields?.circonferenceencm ?: 0,
        espece = this.fields?.espece ?: "",
        hauteurenm = this.fields?.hauteurenm ?: 0)
}

fun Tree.mapToEntity(): TreeEntity {
    return TreeEntity(
        id = this.id,
        adresse = this.adresse,
        circonferenceencm = this.circonferenceencm,
        espece = this.espece,
        hauteurenm = this.hauteurenm)
}

fun TreeEntity.mapToModel(): Tree {
    return Tree(
        id = this.id,
        adresse = this.adresse,
        circonferenceencm = this.circonferenceencm,
        espece = this.espece,
        hauteurenm = this.hauteurenm)
}
