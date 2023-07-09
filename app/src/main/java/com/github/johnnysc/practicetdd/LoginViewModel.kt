package com.github.johnnysc.practicetdd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoginViewModel(
    private val communication: LoginCommunication,
    private val interactor: LoginInteractor,
    private val mapper: WeatherUiMapper<WeatherUiModel>,
    private val validateEmail: UiValidator,
    private val validatePassword: UiValidator,
    private val dispatchers: DispatchersList
) : ViewModel() {
    
    fun login(email: String, password: String) {
        CoroutineScope(dispatchers.io()).launch {
            val isEmailValid = validateEmail.isValid(email)
            val isPasswordValid = validatePassword.isValid(password)
            
            val result = if (isEmailValid) {
                if (isPasswordValid) {
                    val weatherItem: WeatherUiModel = interactor.login().map(mapper)
                    if (weatherItem.isError) LoginState.Error(weatherItem)
                    else LoginState.Success(weatherItem)
                } else {
                    LoginState.PasswordError(validatePassword.errorMessage())
                }
            } else {
                if (isPasswordValid) {
                    LoginState.EmailError(validateEmail.errorMessage())
                } else {
                    LoginState.TwoErrors(
                        validateEmail.errorMessage(),
                        validatePassword.errorMessage()
                    )
                }
            }
            communication.map(result)
        }
    }
}