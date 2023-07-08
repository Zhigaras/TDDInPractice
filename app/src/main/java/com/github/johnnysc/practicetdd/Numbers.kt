package com.github.johnnysc.practicetdd

interface Numbers {
    
    fun isSumLong(): Boolean
    
    fun sumInt(): Int
    
    fun sumLong(): Long
    
    fun difference(): Int
    
    fun divide(): Double
    
    class Base(private val number1: Int, private val number2: Int) : Numbers {
        
        private var isChecked: Boolean = false
        
        override fun isSumLong(): Boolean {
            isChecked = true
            return if (number1 < 1) {
                number2 < Int.MIN_VALUE - number1
            } else {
                number2 > Int.MAX_VALUE - number1
            }
        }
        
        override fun sumInt(): Int {
            if (isChecked.not()) throw IllegalAccessException()
            if (isSumLong()) throw IllegalStateException()
            return number1 + number2
        }
        
        override fun sumLong(): Long {
            if (isChecked.not()) throw IllegalAccessException()
            if (isSumLong().not()) throw IllegalStateException()
            return number1.toLong() + number2
        }
        
        override fun difference(): Int {
            return number1 - number2
        }
        
        override fun divide(): Double {
            if (number2 == 0) throw IllegalArgumentException()
            return number1.toDouble() / number2
        }
    }
}