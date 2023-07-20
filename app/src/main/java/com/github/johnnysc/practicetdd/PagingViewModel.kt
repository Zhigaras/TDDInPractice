package com.github.johnnysc.practicetdd

class PagingViewModel(
    private val repository: PagingRepository,
    private val communication: Communication
) {
    
    fun init(isFirstRun: Boolean) {
        if (isFirstRun)
            repository.messages(PagingRepository.Strategy.INITIAL).let {
                communication.map(it.map { messageDomain -> messageDomain.toUi() })
            }
    }
    
    fun loadMore() {
        repository.messages(PagingRepository.Strategy.NEXT).let {
            communication.map(it.map { messageDomain -> messageDomain.toUi() })
        }
    }
    
    fun loadPrevious() {
        repository.messages(PagingRepository.Strategy.PREVIOUS).let {
            communication.map(it.map { messageDomain -> messageDomain.toUi() })
        }
    }
}