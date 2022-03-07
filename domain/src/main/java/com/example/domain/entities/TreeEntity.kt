package com.example.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TreeEntity(
    val id: String,
    val adresse: String,
    val circonferenceencm: Int,
    val espece: String,
    val hauteurenm: Int
): Parcelable