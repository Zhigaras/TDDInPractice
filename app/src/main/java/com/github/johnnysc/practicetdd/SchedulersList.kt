package com.github.johnnysc.practicetdd

interface SchedulersList {
    
    fun io(): Scheduler
    
    fun ui(): Scheduler
}

object Schedulers {
    
    fun trampoline(): Scheduler = Scheduler.Base()
    
}

interface Scheduler {
    class Base : Scheduler
}