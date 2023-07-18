package com.github.johnnysc.practicetdd

interface FeatureChain {
    
    interface Handle {
        
        suspend fun handle(message: String): MessageUI
    }
    
    interface CheckAndHandle : Handle {
        
        fun canHandle(message: String): Boolean
    }
    
    object Empty : Handle {
        override suspend fun handle(message: String): MessageUI {
            return MessageUI.Empty()
        }
    }
}