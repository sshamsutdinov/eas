package ru.eas.rest

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    companion object : Logging

    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun handleException(exc: Exception): Response {
        logger.error(exc)

        return Response.Fail(exc.toString())
    }
}