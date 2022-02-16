package com.example.data.data_source.remote.responses

data class Trees(
    val facet_groups: List<FacetGroup>? = null,
    val nhits: Int? = null,
    val parameters: Parameters? = null,
    val records: List<Record>? = null
)