package com.github.johnnysc.practicetdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

class MessagesViewModel(
    private val dispatchersList: DispatchersList,
    private val communication: MessagesCommunication.Mutable,
    private val viewModelChain: ViewModelChain
) : ViewModel() {
    
    fun handleInput(message: String) {
        dispatchersList.launchBackground(viewModelScope) {
            val result = viewModelChain.handle(message)
            dispatchersList.changeToUI {
                communication.map(MessageUI.User(message))
                communication.map(result)
            }
        }
    }
}
