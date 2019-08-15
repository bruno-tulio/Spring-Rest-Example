package com.homeio.forum.handler

import org.springframework.http.HttpStatus

class ErrorReponse(val status: Int,
                   val errors: List<ErrorDetalhe>) {

    companion object{

        fun of(httpStatus: HttpStatus, error:  ErrorDetalhe)  : ErrorReponse {
            return ErrorReponse( status =  httpStatus.value(), errors = listOf(error))
        }

        fun of(httpStatus: HttpStatus, errors : List<ErrorDetalhe>): ErrorReponse {
            return ErrorReponse( status =  httpStatus.value(), errors = errors)
        }
    }


    class ErrorDetalhe(val mensagem: String,
                       val mensagemDesenvolvedor: String = mensagem)

}