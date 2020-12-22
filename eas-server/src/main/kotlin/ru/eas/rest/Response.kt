package ru.eas.rest

import com.fasterxml.jackson.annotation.JsonInclude

sealed class Response(open val status: String) {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    data class Success<T : Any>(
        val data: T? = null
    ) : Response("success")

    data class Fail(
        val message: String
    ) : Response("fail")
}