package com.github.johnnysc.practicetdd

interface Repository {
    
    fun fetch(): Single<String>
}

class Single<T>(val data: T) {
    
    companion object {
        fun just(message: String) = Single(message)
        
        fun error(e: Exception) = Single(e.message ?: "")
    }
}