package com.avall.ms.attachments.application.config;

import com.avall.ms.attachments.arch.enums.ErrorEnum
import com.avall.ms.attachments.arch.errors.ErrorDTO
import com.avall.ms.attachments.arch.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

	@ExceptionHandler(value = [(Exception::class)])
	fun handleException(exception:Exception):ResponseEntity<ErrorDTO>  {
		return ResponseEntity(
			ErrorDTO(
				ErrorEnum.INTERNAL_SERVER_ERROR.getCode(),
				exception.localizedMessage),
			HttpStatus.INTERNAL_SERVER_ERROR
		)
	}

	@ExceptionHandler(value = [(NotFoundException::class)])
	fun handleNotFoundException(exception:NotFoundException):ResponseEntity<ErrorDTO>  {
		return ResponseEntity(
			ErrorDTO(
				exception.code,
				exception.message ?: exception.localizedMessage),
			HttpStatus.INTERNAL_SERVER_ERROR
		)
	}

	/**
	 * Handles any handleRuntimeException from the rest controller.
	 *
	 * @param exception IntrusionException
	 * @return error response POJO
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = [(RuntimeException::class)])
	fun handleRuntimeException(exception:RuntimeException):ResponseEntity<ErrorDTO> {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(
					ErrorDTO(
					ErrorEnum.INTERNAL_SERVER_ERROR.getCode(),
					exception.localizedMessage)
				)
	}
}
