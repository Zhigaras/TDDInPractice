package com.github.johnnysc.practicetdd

interface CustomObservable<T : CustomObject, R : CustomObserver<T>> : Update<T> {
    
    fun addObserver(observer: R)
    
    class Base<T : CustomObject, R : CustomObserver<T>>(
        private val segregateContent: SegregateContent<T, R> = SegregateContent.Base()
    ) : CustomObservable<T, R> {
        
        private val observersList = mutableListOf<R>()
        
        override fun addObserver(observer: R) {
            observersList.add(observer)
        }
        
        override fun update(obj: T) {
            observersList.forEach {
                if (segregateContent.isAllowed(obj, it)) it.update(obj)
            }
        }
    }
}

interface CustomObserver<T : CustomObject> : Update<T> {
    
    abstract class Usual<T : CustomObject> : CustomObserver<T>
    
    abstract class Premium<T : CustomObject> : CustomObserver<T>
}

interface CustomObject {
    
    abstract class Premium : CustomObject
    
    abstract class Usual : CustomObject
}

interface Update<T : CustomObject> {
    
    fun update(obj: T)
}

interface SegregateContent<T : CustomObject, R : CustomObserver<T>> {
    
    fun isAllowed(obj: T, observer: R): Boolean
    
    class Base<T : CustomObject, R : CustomObserver<T>> : SegregateContent<T, R> {
        
        override fun isAllowed(obj: T, observer: R): Boolean {
            return if (obj is CustomObject.Usual) true
            else {
                observer is CustomObserver.Premium<*>
            }
        }
    }
}