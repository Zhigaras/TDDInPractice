package com.github.johnnysc.practicetdd

interface RangeLimits {
    
    fun pair(number: Int): RangePair
    
    class Base(private val list: List<Int>) : RangeLimits {
        
        override fun pair(number: Int): RangePair {
            var left = Int.MIN_VALUE
            var right = Int.MAX_VALUE
            for (it in list) {
                if (it < number) left = it
                if (it > number) {
                    right = it
                    break
                }
            }
            return RangePair(left, right)
        }
    }
}

data class RangePair(val left: Int, val right: Int)