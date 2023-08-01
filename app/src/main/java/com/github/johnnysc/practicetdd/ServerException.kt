package com.github.johnnysc.practicetdd

import com.google.gson.annotations.SerializedName

data class ServerException(
    override val message: String,
    private val errorType: String = ""
) : Exception()

data class ApiError(
    @SerializedName("errorMessage")
    private val message: String = "default",
    @SerializedName("errorType")
    private val errorType: String = ""
) {
    fun toServerException() = ServerException(message, errorType)
}