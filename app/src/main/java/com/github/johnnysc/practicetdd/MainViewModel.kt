package com.github.johnnysc.practicetdd

import androidx.lifecycle.ViewModel

class MainViewModel(
    private val filters: MutableList<GoodFilter>,
    private val products: MutableList<Good>,
    private val communication: Communication<List<Good>>,
    private val filterCommunication: Communication<List<GoodFilter>>
) : ViewModel(), Change {
    
    init {
        communication.map(products)
        filterCommunication.map(filters)
    }
    
    override fun change(filter: GoodFilter) {
        filters.find { it == filter }?.change()
        filterCommunication.map(filters)
        products.filter { it.map(Good.Base(filters.filter { it.isChosen() })) }
            .let { communication.map(it) }
    }
}

interface Change {
    
    fun change(filter: GoodFilter)
}