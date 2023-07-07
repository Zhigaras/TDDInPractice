package com.github.johnnysc.practicetdd

interface Contains {
    
    fun contains(collection: List<String>, item: String): Boolean
    
    class Base(private val forWrapper: For) : Contains {
        
        override fun contains(collection: List<String>, item: String): Boolean {
            var count = 0
            forWrapper.repeat(collection.size) {
                val isContains = collection[it] == item
                if (isContains) count++
                isContains
            }
            return count > 0
        }
    }
}