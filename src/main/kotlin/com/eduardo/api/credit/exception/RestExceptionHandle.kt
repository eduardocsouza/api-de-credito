package com.eduardo.api.credit.exception

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandle {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidException(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionDetails> {
        val erros: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors
            .stream()
            .forEach { erro: ObjectError ->
                val fieldName: String = (erro as FieldError).field
                val messageError: String? = erro.defaultMessage
                erros[fieldName] = messageError
            }
        return ResponseEntity(
            ExceptionDetails(
                title = "Bad request!",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = ex.javaClass.toString(),
                details = erros
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(DataAccessException::class)
    fun handleVolationException(ex: DataAccessException): ResponseEntity<ExceptionDetails>{
       return ResponseEntity.status(HttpStatus.CONFLICT)
           .body(
                ExceptionDetails(
                    title = "Esse usuário já existe!",
                    timestamp = LocalDateTime.now(),
                    status = HttpStatus.CONFLICT.value(),
                    exception = ex.javaClass.toString(),
                    details = mutableMapOf(ex.cause.toString() to ex.message)
                )
           )

    }

    @ExceptionHandler(MyExceptio::class)
    fun handleRuntimeException(ex: MyExceptio): ResponseEntity<ExceptionDetails>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                ExceptionDetails(
                title = "Bad Request!",
                timestamp = LocalDateTime.now(),
                 exception = ex.javaClass.toString(),
                 status = HttpStatus.BAD_REQUEST.value(),
                 details = mutableMapOf(ex.cause.toString() to ex.menssage)
            )
            )
    }

}