package hello.study.web.exhandler.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import hello.study.web.exception.UserException;
import hello.study.web.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice("hello.study.web.api")
public class ExControllerAdvice {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ErrorResult illegalExHandle(IllegalArgumentException e) {
		log.error("[exceptionHandle] ex", e);
		return new ErrorResult("BAD", e.getMessage());
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResult> userException(UserException e) {
		return new ResponseEntity<ErrorResult>(new ErrorResult("USER-exception", e.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ErrorResult exHandle(Exception e) {
		return new ErrorResult("error.bad", "내부 오류");
	}

}
