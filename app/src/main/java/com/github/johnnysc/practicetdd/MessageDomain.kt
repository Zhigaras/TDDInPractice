package com.github.johnnysc.practicetdd

interface MessageDomain {
    
    fun toUi(): MessageUi
    
    class Base(private val id: Int, private val text: String) : MessageDomain {
        override fun toUi() = MessageUi.Base(id, text)
    }
    
    object LoadMore: MessageDomain {
        override fun toUi() = MessageUi.LoadMore
    }
    
    object LoadPrevious : MessageDomain {
        override fun toUi() = MessageUi.LoadPrevious
    }
}