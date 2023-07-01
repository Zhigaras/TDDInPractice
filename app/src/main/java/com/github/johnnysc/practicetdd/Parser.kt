package com.github.johnnysc.practicetdd

interface Parser {
    
    fun parse(raw: String): List<Any>
    
    class Base(private val delimiter: String) : Parser {
        
        init {
            if (delimiter.isEmpty()) throw IllegalStateException()
        }
        
        inner class SingleItemParser {
            
            fun parse(item: String): Any {
                if (item == "true") return true
                if (item == "false") return false
                return try {
                    java.lang.Byte.valueOf(item)
                } catch (_: Exception) {
                    try {
                        java.lang.Short.valueOf(item)
                    } catch (_: Exception) {
                        try {
                            Integer.valueOf(item)
                        } catch (_: Exception) {
                            try {
                                java.lang.Long.valueOf(item)
                            } catch (_: Exception) {
                                try {
                                    val float = java.lang.Float.valueOf(item)
                                    if (float == Float.NEGATIVE_INFINITY || float == Float.POSITIVE_INFINITY)
                                        throw IllegalStateException()
                                    else return float
                                } catch (_: Exception) {
                                    try {
                                        java.lang.Double.valueOf(item)
                                    } catch (_: Exception) {
                                        if (item.length == 1) return Character.valueOf(item[0])
                                        else return item
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        private val singleItemParser = SingleItemParser()
        
        override fun parse(raw: String): List<Any> {
            if (raw.isEmpty()) return emptyList()
            return raw.split(delimiter).filter { it.isNotBlank() }.map { singleItemParser.parse(it) }
        }
    }
}

