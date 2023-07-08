package com.github.johnnysc.practicetdd

interface NewInt {
    
    fun isValid(number: Int): Boolean
    
    class Positive(private val newInt: NewInt = ValidInt()) : NewInt {
        
        override fun isValid(number: Int) = number > 0 && newInt.isValid(number)
        
    }
    
    class Negative : NewInt {
        
        override fun isValid(number: Int) = number < 0
    }
    
    class Odd(private val newInt: NewInt = ValidInt()) : NewInt {
        
        override fun isValid(number: Int) = number % 2 != 0 && newInt.isValid(number)
        
    }
    
    class Less(private val limit: Int) : NewInt {
        
        override fun isValid(number: Int) = number < limit
        
    }
    
    class ValidInt : NewInt {
        
        override fun isValid(number: Int) = true
    }
}