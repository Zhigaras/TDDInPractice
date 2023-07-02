package com.github.johnnysc.practicetdd

import android.os.Build
import androidx.annotation.RequiresApi

/**
 * @author Asatryan on 26.12.2022
 */
data class SimpleData(private val value: String)

interface Now {
    
    fun now(): Long
}

interface CacheDataSource {
    
    fun add(item: SimpleData)
    
    fun data(): List<SimpleData>
    
    @RequiresApi(Build.VERSION_CODES.N)
    class Timed(private val now: Now, private val lifeTimeMillis: Long): CacheDataSource {
    
        private val data = mutableListOf<Pair<Long, SimpleData>>()
        
        override fun add(item: SimpleData) {
            data.add(now.now() to item)
        }
    
        override fun data(): List<SimpleData> {
            data.removeIf { it.first + lifeTimeMillis < now.now() }
            return data.map { it.second }
        }
    }
}