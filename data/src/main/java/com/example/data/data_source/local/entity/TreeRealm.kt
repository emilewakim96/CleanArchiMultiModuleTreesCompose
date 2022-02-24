package com.example.data.data_source.local.entity

import androidx.annotation.NonNull
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TreeRealm(
    @PrimaryKey @NonNull var id: String = "",
    var adresse: String = "",
    var circonferenceencm: Int = 0,
    var espece: String = "",
    var hauteurenm: Int = 0
) : RealmObject()