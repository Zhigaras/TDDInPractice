package com.github.johnnysc.practicetdd

interface MessageUI {
    
    fun copyWithId(id: Int): MessageUI
    
    data class Ai(private val text: String, private val id: String = "0") : MessageUI {
        
        override fun copyWithId(id: Int) = copy(id = id.toString())
    }
    
    data class AiError(private val text: String, private val id: String = "0") : MessageUI {
        
        override fun copyWithId(id: Int) = copy(id = id.toString())
    }
    
    data class User(private val text: String, private val id: String = "0") : MessageUI {
        
        override fun copyWithId(id: Int) = copy(id = id.toString())
    }
    
    data class Empty(private val text: String = "", private val id: String = "") : MessageUI {
        
        override fun copyWithId(id: Int) = copy(id = id.toString())
    }
}
