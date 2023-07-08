package com.github.johnnysc.practicetdd

interface TicketFactory {
    
    fun ticket(number: Int): LotteryTicket
    
    class Base : TicketFactory {
        
        override fun ticket(number: Int): LotteryTicket {
            return LotteryTicket.Base(number)
        }
    }
}

interface LotteryTicket {
    
    fun isFake(): Boolean
    
    fun isWinner(): Boolean
    
    class Base(private val number: Int) : LotteryTicket {
        
        override fun isFake(): Boolean {
            return number < 10 || number > 9999_9999 || number.toString().length % 2 != 0
        }
        
        override fun isWinner(): Boolean {
            val stringNumber = number.toString()
            val leftPart = stringNumber.dropLast(stringNumber.length / 2)
            val rightPart = stringNumber.drop(stringNumber.length / 2)
            val list = listOf(leftPart, rightPart).map { str ->
                str.map { char -> char.digitToInt() }.sum()
            }
            return list.first() == list.last()
        }
    }
}