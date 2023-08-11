package com.github.johnnysc.practicetdd

interface Good {
    
    fun <T> map(mapper: Mapper<T>): T
    
    interface Mapper<T> {
        
        fun map(
            ram: Int,
            os: OS,
            displaySize: Double,
            processor: ProcessorType,
            price: Double
        ): T
    }
    
    class Base(private val filters: List<GoodFilter>) : Mapper<Boolean> {
        
        override fun map(
            ram: Int,
            os: OS,
            displaySize: Double,
            processor: ProcessorType,
            price: Double
        ) = filters.all { it.map(ram, os, displaySize, processor, price) }
    }
    
}
