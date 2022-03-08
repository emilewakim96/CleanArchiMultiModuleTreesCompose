package com.example.domain.mappers

import com.example.data.data_source.remote.responses.Tree
import com.example.domain.entities.TreeEntity

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