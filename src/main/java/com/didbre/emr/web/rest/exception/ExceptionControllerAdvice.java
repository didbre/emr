package com.didbre.emr.web.rest.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

/**
 * Controller for exceptions generate through the webservice layer
 *
 * <p>todo the controller can't catch exception generated from api url that didn't exist (like
 * $server:$port/api/vron)
 */
@ControllerAdvice
public class ExceptionControllerAdvice
{

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseBody
  public ResponseEntity<ApiError> handleEntityNotFoundException(
      HttpServletRequest req, Exception ex) {
    return new ResponseEntity<>(
        new ApiError(HttpStatus.NOT_FOUND.value(), req.getRequestURI()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  @ResponseBody
  public ResponseEntity<ApiError> handleHttpMediaTypeNotSupportedException(
      HttpServletRequest req, Exception ex) {
    return new ResponseEntity<>(
        new ApiError(
            HttpStatus.NOT_ACCEPTABLE.value(), "Only media/type < application/json > is accepted", req.getRequestURI()),
        HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseBody
  public ResponseEntity<ApiError> handleNoHandlerFoundException(
      HttpServletRequest req, Exception ex) {
    return new ResponseEntity<>(
        new ApiError(
            HttpStatus.NOT_ACCEPTABLE.value(), "FUCK!!!", req.getRequestURI()),
        HttpStatus.NOT_ACCEPTABLE);
  }

  /**
   * Exception generated when a POST is tried without data
   * @param req
   * @param ex
   * @return
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseBody
  public ResponseEntity<ApiError> handleHttpMessageNotReadableException(
      HttpServletRequest req, Exception ex) {
    return new ResponseEntity<>(
        new ApiError(
            HttpStatus.NOT_ACCEPTABLE.value(), "Required request body is missing", req.getRequestURI()),
        HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseBody
  public ResponseEntity<ApiError> handleNoSuchElementException(
      HttpServletRequest req, Exception ex) {
    return new ResponseEntity<>(
        new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), req.getRequestURI()),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NumberFormatException.class)
  @ResponseBody
  public ResponseEntity<ApiError> NumberFormatException(HttpServletRequest req, Exception ex) {
    //    todo not working
    return new ResponseEntity<>(
        new ApiError(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage(), req.getRequestURI()),
        HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  @ResponseBody
  public ResponseEntity<ApiError> handleEmptyResultDataAccessException(
      HttpServletRequest req, Exception ex) {
    return new ResponseEntity<>(
        new ApiError(
            HttpStatus.NOT_ACCEPTABLE.value(), checkMessage(ex.getMessage()), req.getRequestURI()),
        HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<ApiError> handleException(HttpServletRequest req, Exception ex) {
    return new ResponseEntity<>(
        new ApiError(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            checkMessage(ex.getMessage()),
            req.getRequestURI()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * If no message is part of the exception, send something meaningful
   *
   * @param msg Error message
   * @return Either the new message or the error message
   */
  private String checkMessage(String msg) {
    if (StringUtils.isEmpty(msg)) {
      return "No error msg in the exception";
    } else {
      return msg;
    }
  }
}
