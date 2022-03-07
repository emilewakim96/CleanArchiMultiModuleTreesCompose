package com.example.data.data_source.remote.responses

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

data class Trees(
    val facet_groups: List<FacetGroup>? = null,
    val nhits: Int? = null,
    val parameters: Parameters? = null,
    val records: List<Record>? = null
)

@Entity
@Parcelize
data class Tree(
    @PrimaryKey @NonNull val id: String,
    val adresse: String,
    val circonferenceencm: Int,
    val espece: String,
    val hauteurenm: Int
): Parcelable

data class Record(
    val datasetid: String?,
    val fields: Fields?,
    val geometry: Geometry?,
    val record_timestamp: String?,
    val recordid: String
)

data class Parameters(
    val dataset: String?,
    val facet: List<String>?,
    val format: String?,
    val rows: Int?,
    val start: Int?,
    val timezone: String?
)

@Parcelize
data class Geometry(
    val coordinates: List<Double>?,
    val type: String?
): Parcelable

@Parcelize
data class Fields(
    val adresse: String?,
    val arrondissement: String?,
    val circonferenceencm: Int?,
    val complementadresse: String?,
    val domanialite: String?,
    val espece: String?,
    val genre: String?,
    val geo_point_2d: List<Double>?,
    val hauteurenm: Int?,
    val idbase: Int?,
    val idemplacement: String?,
    val libellefrancais: String?,
    val remarquable: String?,
    val stadedeveloppement: String?,
    val typeemplacement: String?
): Parcelable

data class FacetGroup(
    val facets: List<Facet>?,
    val name: String?
)

data class Facet(
    val count: Int?,
    val name: String?,
    val path: String?,
    val state: String?
)