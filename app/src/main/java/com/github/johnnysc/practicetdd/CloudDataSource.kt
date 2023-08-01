package com.github.johnnysc.practicetdd

import com.google.gson.Gson

interface CloudDataSource {
    
    suspend fun fetch(): UserCloud
    
    class Base(
        private val apiService: ApiService,
        private val serialize: Serialize = Serialize.Base()
    ) : CloudDataSource {
        override suspend fun fetch(): UserCloud {
            val response = apiService.fetch()
            return try {
                response.body()!!
            } catch (_:Exception) {
                val error = serialize.fromString(json = response.errorBody()?.string() ?: "", default = ApiError())
                throw error.toServerException()
            }
        }
    }
}

interface Serialize {
    
    fun <T: Any> fromString(json: String, default: T): T
    
    fun toString(obj: Any): String
    
    class Base(private val gson: Gson = Gson()): Serialize {
    
        override fun <T : Any> fromString(json: String, default: T): T {
            return try {
                gson.fromJson(json, default.javaClass)
            } catch (_: Exception) {
                default
            }
        }
    
        override fun toString(obj: Any): String {
            return gson.toJson(obj)
        }
    }
}