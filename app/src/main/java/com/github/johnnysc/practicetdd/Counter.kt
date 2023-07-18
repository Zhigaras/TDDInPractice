package com.github.johnnysc.practicetdd

interface Counter {
    
    fun increment(messageUI: MessageUI): MessageUI
    
    class Base : Counter {
        
        private var counter = 0
        
        override fun increment(messageUI: MessageUI): MessageUI {
            return messageUI.copyWithId(id = counter++)
        }
    }
}