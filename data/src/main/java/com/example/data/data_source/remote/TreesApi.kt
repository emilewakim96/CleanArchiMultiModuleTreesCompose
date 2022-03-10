package com.example.data.data_source.remote

import com.example.data.data_source.remote.responses.Trees
import retrofit2.http.GET
import retrofit2.http.Headers

const val CACHE_CONTROL_HEADER = "Cache-Control"
const val CACHE_CONTROL_NO_CACHE = "no-cache"

interface TreesApi {

    companion object {
        const val TREES_PATH = "/api/records/1.0/search/?dataset=les-arbres&q=&facet=typeemplacement&facet=domanialite&facet=arrondissement&facet=libellefrancais&facet=genre&facet=espece&facet=varieteoucultivar&facet=circonferenceencm&facet=hauteurenm&facet=stadedeveloppement&facet=remarquable"
    }

    @GET(TREES_PATH)
    suspend fun getTreesList(): Trees

    @GET(TREES_PATH)
    @Headers("$CACHE_CONTROL_HEADER: $CACHE_CONTROL_NO_CACHE")
    suspend fun forceGetTreesList(): Trees /* get data from server and not from cache */


}