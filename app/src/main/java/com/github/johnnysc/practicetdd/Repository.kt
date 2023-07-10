package com.github.johnnysc.practicetdd

interface Repository {
    
    fun fetch(body: String, callback: DataCallback)
    
    class Base(private val api: Api) : Repository {
        
        override fun fetch(body: String, callback: DataCallback) {
            api.fetch(Api.RequestBody(body), Api.Callback())
        }
    }
    
    interface DataCallback {
        
        fun provideSuccess(data: String)
        
        fun provideError(message: String)
        
    }
}

interface Api {
    
    fun fetch(body: RequestBody, callback: Callback)
    
    class RequestBody(private val data: String) {
        
        fun isEmpty(): Boolean = data.isEmpty()
    }
    
    class Callback : ApiResultCallback {
        
        override fun provideError(result: Result): String {
            return result.provideData()
        }
        
        override fun provideSuccess(result: Result): String {
            return result.provideData()
        }
    }
    
    abstract class Result {
        
        abstract fun provideData(): String
        
        data class Error(private val e: Exception) : Result() {
            override fun provideData(): String = e.message.toString()
        }
        
        data class Success(private val data: String) : Result() {
            override fun provideData(): String = data
        }
    }
}

interface ApiResultCallback {
    
    fun provideError(result: Api.Result): String
    
    fun provideSuccess(result: Api.Result): String
}