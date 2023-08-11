package com.github.johnnysc.practicetdd

interface Validation {
    
    fun isValid(text: String): Result
    
    class Password(
        minLength: Int = 1,
        upperCaseLettersCount: Int = 0,
        lowerCaseLettersCount: Int = 0,
        numbersCount: Int = 0,
        specialSignsCount: Int = 0
    ) : Validation {
        
        private val conditionsList = listOf(
            Condition.MinLength(minLength),
            Condition.UpperCaseLettersCount(upperCaseLettersCount),
            Condition.LowerCaseLettersCount(lowerCaseLettersCount),
            Condition.NumbersCount(numbersCount),
            Condition.SpecialSigns(specialSignsCount)
        )
        
        init {
            if (conditionsList.any { it.isParamValid().not() })
                throw java.lang.IllegalStateException()
        }
        
        override fun isValid(text: String): Result {
            val result = conditionsList.map { it.check(text) }
            return if (result.all { it.isValid() }) Result.Valid
            else result.first { it.isValid().not() }
        }
    }
    
    interface Result {
        
        fun isValid(): Boolean
        
        object Valid : Result {
            override fun isValid() = true
        }
        
        abstract class Invalid : Result {
            override fun isValid() = false
        }
        
        data class MinLengthInsufficient(val minLength: Int) : Invalid()
        
        data class UpperCaseLettersCountInsufficient(val upperCaseLettersCount: Int) : Invalid()
        
        data class LowerCaseLettersCountInsufficient(val lowerCaseLettersCount: Int) : Invalid()
        
        data class NumbersCountInsufficient(val numbersCount: Int) : Invalid()
        
        data class SpecialSignsInsufficient(val specialSignsCount: Int) : Invalid()
        
        object NotNeedValidation : Result {
            override fun isValid() = true
        }
    }
}