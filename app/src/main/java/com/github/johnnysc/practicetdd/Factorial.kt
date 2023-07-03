package com.github.johnnysc.practicetdd

interface Factorial<T> {
    
    fun value(number: T): T
    
    class Int : Factorial<kotlin.Int> {
        
        override fun value(number: kotlin.Int): kotlin.Int {
            return if (number == 0) 1
            else number.times(value(number - 1))
        }
    }
    
    class Double : Factorial<kotlin.Double> {
        
        override fun value(number: kotlin.Double): kotlin.Double {
            return if (number == 0.0) 1.0
            else number.times(value(number - 1))
        }
    }
    
    class BigInteger : Factorial<java.math.BigInteger> {
        
        override fun value(number: java.math.BigInteger): java.math.BigInteger {
            return if (number == java.math.BigInteger.ZERO) java.math.BigInteger.ONE
            else number.times(value(number - java.math.BigInteger.ONE))
        }
    }
    
    class Factory(
        private val int: Factorial<kotlin.Int>,
        private val double: Factorial<kotlin.Double>,
        private val bigInteger: Factorial<java.math.BigInteger>
    ) : Factorial<Number> {
        
        override fun value(number: Number): Number {
            
            return when (number) {
                in kotlin.Int.MIN_VALUE until 0 -> throw IllegalArgumentException()
                in 0..12 -> int.value(number.toInt())
                in 13..170 -> double.value(number.toDouble())
                in 171..11000 -> bigInteger.value(java.math.BigInteger.valueOf(number.toLong()))
                else -> throw IllegalStateException()
            }
        }
    }
}