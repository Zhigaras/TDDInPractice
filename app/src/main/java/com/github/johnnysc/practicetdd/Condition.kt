package com.github.johnnysc.practicetdd

abstract class Condition(private val paramValue: Int) {
    
    protected abstract val conditionToCheck: (Char) -> Boolean
    protected abstract val invalidResult: Validation.Result
    
    open fun isParamValid() = paramValue >= 0
    
    open fun check(text: String): Validation.Result {
        if (paramValue > 0) {
            var counter = 0
            text.forEach { char ->
                if (conditionToCheck.invoke(char)) counter++
            }
            return if (counter >= paramValue) Validation.Result.Valid
            else invalidResult
        }
        return Validation.Result.NotNeedValidation
    }
    
    class MinLength(private val paramValue: Int) : Condition(paramValue) {
        
        override val conditionToCheck: (Char) -> Boolean = { true }
        override val invalidResult = Validation.Result.MinLengthInsufficient(paramValue)
        
        override fun isParamValid(): Boolean = paramValue >= 1
        
        override fun check(text: String): Validation.Result {
            return if (text.length < paramValue) invalidResult
            else Validation.Result.NotNeedValidation
        }
    }
    
    class UpperCaseLettersCount(paramValue: Int) : Condition(paramValue) {
        override val conditionToCheck: (Char) -> Boolean = { it.isUpperCase() }
        override val invalidResult = Validation.Result.UpperCaseLettersCountInsufficient(paramValue)
    }
    
    class LowerCaseLettersCount(paramValue: Int) : Condition(paramValue) {
        override val conditionToCheck: (Char) -> Boolean = { it.isLowerCase() }
        override val invalidResult = Validation.Result.LowerCaseLettersCountInsufficient(paramValue)
    }
    
    class NumbersCount(paramValue: Int) : Condition(paramValue) {
        override val conditionToCheck: (Char) -> Boolean = { it.isDigit() }
        override val invalidResult = Validation.Result.NumbersCountInsufficient(paramValue)
    }
    
    class SpecialSigns(paramValue: Int) : Condition(paramValue) {
        override val conditionToCheck: (Char) -> Boolean = { "!@#$%^&* ".contains(it) }
        override val invalidResult = Validation.Result.SpecialSignsInsufficient(paramValue)
    }
}