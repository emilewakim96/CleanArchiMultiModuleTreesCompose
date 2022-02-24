package com.example.data.data_source.remote.mappers

import com.example.data.data_source.local.entity.TreeRealm
import com.example.data.data_source.remote.responses.Record
import com.example.domain.models.Tree

fun Record.mapRecordToTree(): Tree {
    return Tree(
        id = this.recordid,
        adresse = this.fields?.adresse ?: "",
        circonferenceencm = this.fields?.circonferenceencm ?: 0,
        espece = this.fields?.espece ?: "",
        hauteurenm = this.fields?.hauteurenm ?: 0)
}

fun TreeRealm.mapToTree(): Tree {
    return Tree(
        id = this.id,
        adresse = this.adresse,
        circonferenceencm = this.circonferenceencm,
        espece = this.espece,
        hauteurenm = this.hauteurenm)
}

fun Tree.mapToTreeRealm(): TreeRealm {
    return TreeRealm(
        id = this.id,
        adresse = this.adresse,
        circonferenceencm = this.circonferenceencm,
        espece = this.espece,
        hauteurenm = this.hauteurenm)
}