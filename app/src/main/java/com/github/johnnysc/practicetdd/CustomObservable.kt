package com.github.johnnysc.practicetdd

interface CustomObservable<T, R : CustomObserver<T>> {
    
    fun addObserver(observer: R)
    
    fun update(argument: T)
    
    fun removeObserver(observer: R)
    
    class Base<T, R : CustomObserver<T>> : CustomObservable<T, R> {
        
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

interface CustomObserver<T> {
    
    fun update(argument: T)

}