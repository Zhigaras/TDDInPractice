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
        
        private val numberLength = number.toString().length
        
        override fun isFake(): Boolean {
            return number < 10 || number > 9999_9999 || numberLength % 2 != 0
        }
        
        override fun isWinner(): Boolean {
            val stringNumber = number.toString()
            val list = listOf(
                stringNumber.dropLast(numberLength / 2),
                stringNumber.drop(numberLength / 2)
            ).map { str ->
                str.map { char -> char.digitToInt() }.sum()
            }
            return list.first() == list.last()
        }
    }
}