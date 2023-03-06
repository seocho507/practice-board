package dev.seocho507.board.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.seocho507.board.controller.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(MyCustomException.class)
	public ResponseEntity<?> errorHandler(MyCustomException e) {
		log.error("Error occurs {}", e.toString());
		return ResponseEntity.status(e.getErrorCode().getStatus())
							 .body(Response.error(e.getErrorCode().name()));
	}
}
