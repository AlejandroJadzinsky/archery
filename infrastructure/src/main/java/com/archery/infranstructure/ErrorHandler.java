package com.archery.infranstructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/** This is intended to manage Exceptions at application layer, providing an
 * unified and consistent error handling and response message.
 */
@ControllerAdvice
public class ErrorHandler {

  /** The default exception handler for unexpected errors.
   *
   * @param e an Exception instance, cannot be null.
   *
   * @return a ResponseEntity instance, never null.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> onUnexpectedError(final Exception e) {
    ErrorResponse error = new ErrorResponse(e);
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /** The default exception handler for unexpected errors.
   *
   * @param e an Exception instance, cannot be null.
   *
   * @return a ResponseEntity instance, never null.
   */
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> onBusinessError(
      final BusinessException e) {
    ErrorResponse error = new ErrorResponse(e);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
