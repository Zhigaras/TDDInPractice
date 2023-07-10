package com.github.johnnysc.practicetdd

interface CustomObservable<T : Any, R : CustomObserver<T>> : Update<T> {
    
    fun addObserver(observer: R)
    
    class Base<T : Any, R : CustomObserver<T>> : CustomObservable<T, R> {
        
        private val observersList = mutableListOf<R>()
        
        override fun addObserver(observer: R) {
            observersList.add(observer)
        }
        
        override fun update(obj: T) {
            if (obj is CustomObject.Premium) {
                observersList
                    .filterIsInstance<CustomObserver.Premium<T>>()
                    .forEach { it.update(obj) }
            } else {
                observersList.forEach { it.update(obj) }
            }
        }
    }
}

interface CustomObserver<T : Any> : Update<T> {
    
    abstract class Usual<T : Any> : CustomObserver<T>
    
    abstract class Premium<T : Any> : CustomObserver<T>
}

interface CustomObject {
    
    abstract class Premium : CustomObject
    
    abstract class Usual : CustomObject
}

interface Update<T : Any> {
    
    fun update(obj: T)
}