package com.github.johnnysc.practicetdd

class MainViewModel(
    private val repository: Repository,
    private val communication: Communication,
    private val schedulersList: SchedulersList
) {
    
    fun fetch() {
        repository.fetch().let { communication.map(it.data) }
    }
}