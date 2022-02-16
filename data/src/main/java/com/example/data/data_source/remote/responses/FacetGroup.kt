package com.example.data.data_source.remote.responses

import com.example.data.data_source.remote.responses.Facet

data class FacetGroup(
    val facets: List<Facet>?,
    val name: String?
)