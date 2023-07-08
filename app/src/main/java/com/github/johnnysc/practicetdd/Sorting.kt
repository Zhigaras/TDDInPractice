package com.github.johnnysc.practicetdd

interface Sorting {
    
    fun sort(list: List<Int>): List<Int>
    
    class Base(private val forOut: For, private val forIn: For) : Sorting {
        
        override fun sort(list: List<Int>): List<Int> {
            if (list.size < 2) return list
            val sortedList = list.toMutableList()
            forOut.repeat(sortedList.size) {
                var changesCount = 0
                forIn.repeat(sortedList.size - 1) {
                    if (sortedList[it] > sortedList[it + 1]) {
                        val left = sortedList[it]
                        sortedList[it] = sortedList[it + 1]
                        sortedList[it + 1] = left
                        changesCount++
                    }
                    false
                }
                changesCount == 0
            }
            return sortedList
        }
    }
}