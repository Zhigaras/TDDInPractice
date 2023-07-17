package com.github.johnnysc.practicetdd

data class LegacyObject(
    private val text: String,
    private val lambda: () -> Unit
) {
    fun go() {
        lambda.invoke()
    }
}

class Legacy(
    private val text: String,
    private val interaction: Interaction
) {
    
    fun map(): LegacyObject {
        return LegacyObject(text, HandleInteraction(text, interaction))
    }
}