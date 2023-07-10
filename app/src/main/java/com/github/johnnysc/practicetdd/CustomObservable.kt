package com.github.johnnysc.practicetdd

interface CustomObservable<T : Any, R : CustomObserver<T>> : Update<T> {
    
    fun addObserver(observer: R)
    
    fun removeObserver(observer: R)
    
    class Base<T : Any, R : CustomObserver<T>>(private val maxCount: Int) : CustomObservable<T, R> {
        
        private val observersMap = mutableMapOf<Int, R>()
        
        override fun addObserver(observer: R) {
            observersMap[observer.hashCode()] = observer
        }
        
        override fun removeObserver(observer: R) {
            observersMap.remove(observer.hashCode())
        }
        
        override fun update(argument: T) {
            observersMap.toList().reversed().take(maxCount).forEach {
                it.second.update(argument)
            }
        }
    }
}

interface CustomObserver<T : Any> : Update<T>

interface Update<T : Any> {
    fun update(argument: T)
}