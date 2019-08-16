package com.homeio.forum.handler

import com.homeio.forum.v1.repository.excption.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.NoSuchMessageException
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.validation.BindException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


private const val NO_MESSAGE_AVAILABLE = "No message available"


@RestControllerAdvice
class ApiHandlerException {


    @Autowired
    private lateinit var messageSource : MessageSource

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<Any> {
       val errorsDetalhes=  ex.bindingResult
                .fieldErrors
                .map { createErroDetalhe(it) }

        return error(HttpStatus.BAD_REQUEST, errorsDetalhes)
    }

    @ExceptionHandler(BindException::class)
    fun handlerBindException(ex: BindException): ResponseEntity<Any> {
        val errorsDetalhes=  ex.bindingResult
                .fieldErrors
                .map { createErroDetalhe(it) }

        return error(HttpStatus.BAD_REQUEST, errorsDetalhes)
    }


    @ExceptionHandler(EntityNotFoundException::class)
    fun handlerEntityNotFoundException(ex: EntityNotFoundException): ResponseEntity<Any> {
        return error(HttpStatus.NOT_FOUND, ErrorReponse.ErrorDetalhe(mensagem = ex.message!!))
    }


    @ExceptionHandler(UsernameNotFoundException::class)
    fun handlerUsernameNotFoundException(ex: UsernameNotFoundException): ResponseEntity<Any> {
        return error(HttpStatus.UNAUTHORIZED, ErrorReponse.ErrorDetalhe(mensagem ="Usuario e/ou senha inválida"))
    }

    private fun error(status: HttpStatus, error: ErrorReponse.ErrorDetalhe): ResponseEntity<Any> {
        return ResponseEntity(ErrorReponse.of(status, error), status)
    }

    private fun error(status: HttpStatus, errors: List<ErrorReponse.ErrorDetalhe>): ResponseEntity<Any> {
        return ResponseEntity(ErrorReponse.of(status, errors), status)
    }


    private fun createErroDetalhe(fieldError: FieldError): ErrorReponse.ErrorDetalhe {
        var message: String
        try {
            message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale())
        } catch (ex: NoSuchMessageException) {
            message = NO_MESSAGE_AVAILABLE
        }

        return ErrorReponse.ErrorDetalhe(message, fieldError.toString())

    }



}