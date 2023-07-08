package com.github.johnnysc.practicetdd

interface Count {
    
    fun click()
    
    class Base(private val callback: Callback) : Count {
    
        private var count = 0
        
        override fun click() {
            count++
            callback.provide(count.toString())
        }
    }
    
    interface Callback {
    
        fun provide(value: String)
    }
}