package com.example.data.data_source.remote

import com.example.data.data_source.remote.responses.Trees
import retrofit2.http.GET

interface TreesApi {

    companion object {
        const val TREES_PATH = "/api/records/1.0/search/?dataset=les-arbres&q=&facet=typeemplacement&facet=domanialite&facet=arrondissement&facet=libellefrancais&facet=genre&facet=espece&facet=varieteoucultivar&facet=circonferenceencm&facet=hauteurenm&facet=stadedeveloppement&facet=remarquable"
    }

    @GET(TREES_PATH)
    suspend fun getTreesList(): Trees
}