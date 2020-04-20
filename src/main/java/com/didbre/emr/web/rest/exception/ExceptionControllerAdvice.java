package com.didbre.emr.web.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class ExceptionControllerAdvice {

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseBody
  public ResponseEntity<ErrorInfo> handleEntityNotFoundException(
      HttpServletRequest req, Exception ex) {
    return new ResponseEntity<>(
        new ErrorInfo(HttpStatus.NOT_FOUND.value(), req.getRequestURI()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseBody
  public ResponseEntity<ErrorInfo> handleNoSuchElementException(
      HttpServletRequest req, Exception ex) {
    return new ResponseEntity<>(
        new ErrorInfo(HttpStatus.NOT_FOUND.value(), ex.getMessage(), req.getRequestURI()),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<ErrorInfo> handleException(HttpServletRequest req, Exception ex) {
    return new ResponseEntity<>(
        new ErrorInfo(
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
