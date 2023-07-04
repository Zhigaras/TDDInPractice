package com.github.johnnysc.practicetdd

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface Communication<T> {
    
    fun observe(owner: LifecycleOwner, observer: Observer<T>)
    
    fun map(source: T)
    
}