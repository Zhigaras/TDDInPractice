package com.github.johnnysc.practicetdd

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface MessagesCommunication {
    
    interface Observe {
        fun observe(owner: LifecycleOwner, observer: Observer<List<MessageUI>>)
    }
    
    interface Map {
        fun map(data: MessageUI)
    }
    
    interface Mutable : Map, Observe
}