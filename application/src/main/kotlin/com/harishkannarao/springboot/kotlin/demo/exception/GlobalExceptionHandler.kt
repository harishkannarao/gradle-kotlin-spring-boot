package com.harishkannarao.springboot.kotlin.demo.exception

import com.harishkannarao.springboot.kotlin.demo.dto.ClientErrorResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice(annotations = [RestController::class])
class GlobalExceptionHandler {

    @ExceptionHandler
    fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<ClientErrorResponseDto> {
        val errors = ex.constraintViolations.iterator().asSequence().map { ClientErrorResponseDto.ClientError(it.propertyPath.toString(), it.message) }.toList()
        return ResponseEntity.badRequest()
                .body(ClientErrorResponseDto(
                        message = "Client Error",
                        errors = errors
                ))
    }
}