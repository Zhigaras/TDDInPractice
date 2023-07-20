package com.github.johnnysc.practicetdd

interface MessageUi {
    
    fun toDomain(): MessageDomain
    
    data class Base(private val id: Int, private val text: String): MessageUi {
        override fun toDomain() = MessageDomain.Base(id, text)
    }
    
    object LoadMore: MessageUi {
        override fun toDomain() = MessageDomain.LoadMore
    }
    
    object LoadPrevious: MessageUi {
        override fun toDomain() = MessageDomain.LoadPrevious
    }
}