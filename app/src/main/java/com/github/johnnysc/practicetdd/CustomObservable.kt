package com.github.johnnysc.practicetdd

interface CustomObservable<T : Any, R : CustomObserver<T>> : Update<T> {
    
    fun addObserver(observer: R)
    
    fun removeObserver(observer: R)
    
    class Base<T : Any, R : CustomObserver<T>> : CustomObservable<T, R> {
        
        private val observers = mutableListOf<R>()
        
        override fun addObserver(observer: R) {
            observers.add(observer)
        }
        
        override fun update(argument: T) {
            observers.forEach { it.update(argument) }
        }
        
        override fun removeObserver(observer: R) {
            observers.remove(observer)
        }
    }
}

interface CustomObserver<T : Any> : Update<T>

interface Update<T : Any> {
    
    fun update(argument: T)
}