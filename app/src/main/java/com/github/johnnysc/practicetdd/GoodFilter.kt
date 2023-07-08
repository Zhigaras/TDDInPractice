package com.github.johnnysc.practicetdd

interface GoodFilter : Good.Mapper<Boolean>{
    
    fun change()
    
    fun isChosen(): Boolean
    
    abstract class Abstract : GoodFilter {
        
        private var isChosen: Boolean = false
        
        override fun change() {
            isChosen = !isChosen
        }
        
        override fun isChosen() = isChosen
    }
}