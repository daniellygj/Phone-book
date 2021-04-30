package com.phonebook.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class Exception extends Throwable {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public static class EmptyPhonesException extends RuntimeException {
		public EmptyPhonesException() {
			super("Phones cannot be empty");
		}
	}
}
