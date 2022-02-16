package com.example.common.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Tree(
    @PrimaryKey @NonNull val id: String,
    val adresse: String,
    val circonferenceencm: Int,
    val espece: String,
    val hauteurenm: Int
): Parcelable