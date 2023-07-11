package com.github.johnnysc.practicetdd

class ViewModelChain(private val featureChain: FeatureChain.CheckAndHandle) : FeatureChain.Handle {
    
    private var secondFeatureChain: FeatureChain = FeatureChain.Empty()
    
    fun nextFeatureChain(nextFeatureChain: FeatureChain.Handle) {
        secondFeatureChain = nextFeatureChain
    }
    
    override suspend fun handle(message: String): MessageUI {
        return if (featureChain.canHandle(message)) featureChain.handle(message)
        else secondFeatureChain.handle(message)
    }
}

interface FeatureChain {
    
    suspend fun handle(message: String): MessageUI
    
    interface Handle : FeatureChain
    
    interface CheckAndHandle : FeatureChain {
        
        fun canHandle(message: String): Boolean
    }
    
    class Empty : Handle {
        
        override suspend fun handle(message: String): MessageUI {
            return MessageUI.Empty
        }
    }
}

interface MessageUI {
    
    object Empty : MessageUI
}