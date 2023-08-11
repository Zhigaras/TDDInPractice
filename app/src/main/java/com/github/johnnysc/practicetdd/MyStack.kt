package com.github.johnnysc.practicetdd

interface MyStack<T> {
    
    fun pop(): T
    
    fun push(item: T)
    
    class FIFO<T>(maxCount: Int) : AbstractStack<T>(maxCount) {
        
        override fun pop(): T {
            if (position == -1) {
                throw java.lang.IllegalStateException("The stack is empty.")
            } else {
                val firstItem = array[0]
                    ?: throw java.lang.IllegalStateException("The stack is empty.")
                for (i in 0 until array.lastIndex) {
                    array[i] = array[i + 1]
                }
                array[array.lastIndex] = null
                position--
                return firstItem
            }
        }
    }
    
    class LIFO<T>(maxCount: Int) : AbstractStack<T>(maxCount) {
        
        override fun pop(): T {
            if (position == -1) {
                throw java.lang.IllegalStateException("The stack is empty.")
            } else {
                val lastItem = array[position]
                    ?: throw java.lang.IllegalStateException("The stack is empty.")
                array[position--] = null
                return lastItem
            }
        }
    }
}

abstract class AbstractStack<T>(maxCount: Int) : MyStack<T> {
    
    init {
        if (maxCount <= 0) throw java.lang.IllegalStateException("max count should be positive")
    }
    
    protected val array = MutableList<T?>(maxCount) { null }
    
    protected var position: Int = -1
    
    override fun push(item: T) {
        if (position == array.lastIndex)
            throw java.lang.IllegalStateException("Stack overflow exception, maximum is ${array.size}")
        array[++position] = item
    }
}