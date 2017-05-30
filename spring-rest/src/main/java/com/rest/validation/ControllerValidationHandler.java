package com.rest.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.rest.dto.MessageDTO;
import com.rest.exception.UserNotFoundException;

@ControllerAdvice
public class ControllerValidationHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public List<MessageDTO> processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();

		return processFieldError(result.getAllErrors());
	}

	private List<MessageDTO> processFieldError(List<ObjectError> error) {
		List<MessageDTO> errorList = new ArrayList<>();

		for (ObjectError objectError : error) {

			Locale currentLocale = LocaleContextHolder.getLocale();

			String msg = messageSource.getMessage(objectError.getDefaultMessage(), null, currentLocale);

			MessageDTO message = new MessageDTO(msg, objectError.getDefaultMessage());
			errorList.add(message);
		}
		return errorList;
	}

	@ExceptionHandler(value = UserNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ResponseBody
	public MessageDTO userNotFound(UserNotFoundException e) {
		Locale currentLocale = LocaleContextHolder.getLocale();

		String msg = messageSource.getMessage(e.getErrorCode(), null, currentLocale);
		return new MessageDTO(msg, e.getErrorCode());
	}

	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public MessageDTO missingRequestParameter(MissingServletRequestParameterException e) {
		Locale currentLocale = LocaleContextHolder.getLocale();

		String msg = messageSource.getMessage("spring.bind.missingparameter", null, currentLocale);
		return new MessageDTO(msg, "spring.bind.missingparameter");
	}

	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public MessageDTO methodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {

		return new MessageDTO(e.getMessage(), "Bad request");
	}
}