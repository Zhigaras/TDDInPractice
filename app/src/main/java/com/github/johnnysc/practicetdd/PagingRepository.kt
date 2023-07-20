package com.github.johnnysc.practicetdd

interface PagingRepository {
    
    enum class Strategy {
        INITIAL,
        NEXT,
        PREVIOUS
    }
    
    fun messages(strategy: Strategy): List<MessageDomain>
    
}