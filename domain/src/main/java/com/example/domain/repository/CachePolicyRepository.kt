package com.example.domain.repository

import com.example.domain.util.CachePolicy

abstract class CachePolicyRepository<T>(
    private val localDataSource: LocalDataSource<CacheEntry<T>>,
    private val remoteDataSource: RemoteDataSource<T>
) {

    /*
    https://thewerner.medium.com/android-repository-caching-with-mvvm-and-clean-architecture-7fb7b691e0e4
    NEVER — “Just fetch the latest data and return it (and don’t cache it)”
    CLEAR — “If you have a cache line for this key, return it, but also then delete it (so any future calls will be forced to fetch),
            otherwise just return the fetched copy as you will with the NEVER policy type”
    EXPIRES — “If you have a cache line for this key not older than the given timestamp, return it,
            otherwise, delete the entry and return (and cache) a fetched copy”
    ALWAYS, // always create a cache line for the key
    REFRESH, // re-fetch (refresh) the cache line for the key
    */

    suspend fun fetch(cachePolicy: CachePolicy): T? {
        return when (cachePolicy.type) {
            CachePolicy.Type.NEVER -> remoteDataSource.fetch()
            CachePolicy.Type.ALWAYS -> {
                localDataSource.get()?.value ?: fetchAndCache()
            }
            CachePolicy.Type.CLEAR -> {
                localDataSource.get()?.value.also {
                    localDataSource.clear()
                }
            }
            CachePolicy.Type.REFRESH -> fetchAndCache()
            CachePolicy.Type.EXPIRES -> {
                localDataSource.get()?.let {
                    if( (it.createdAt + cachePolicy.expires) > System.currentTimeMillis()) {
                        it.value
                    } else {
                        fetchAndCache()
                    }
                } ?: fetchAndCache()
            }
            else -> null
        }
    }

    private suspend fun fetchAndCache(): T {
        return remoteDataSource.fetch().also {
            localDataSource.set(CacheEntry(value = it))
        }
    }
}

//data class CacheEntry<T>(
//    val key: String,
//    val value: T,
//    val createdAt: Long = System.currentTimeMillis()
//)

//interface LocalDataSource<in Key : Any, T> {
//    fun get(key: Key): T?
//    fun set(key: Key, value: T)
//    fun remove(key: Key)
//    fun clear()
//}

data class CacheEntry<T>(
    val value: T,
    val createdAt: Long = System.currentTimeMillis()
)

interface LocalDataSource<T> {
    suspend fun get(): T?
    suspend fun set(cacheEntry: T)
    suspend fun clear()
}

interface RemoteDataSource<T> {
    suspend fun fetch(): T
}