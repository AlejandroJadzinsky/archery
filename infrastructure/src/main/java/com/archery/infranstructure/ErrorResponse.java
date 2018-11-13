package com.archery.infranstructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.NestedExceptionUtils;

/** The Application {@link ErrorResponse}.
 */
public class ErrorResponse {
  @JsonProperty
  private String type;
  @JsonProperty
  private String message;
  @JsonProperty
  private String rootType;
  @JsonProperty
  private String rootMessage;
  @JsonProperty
  private String entity;
  @JsonProperty
  private String process;

  /** Constructor for default Exception.
   *
   * @param exception the Exception instance, cannot be null.
   */
  ErrorResponse(final Exception exception) {
    type = exception.getClass().getCanonicalName();
    message = exception.getMessage();
    Throwable root = NestedExceptionUtils.getRootCause(exception);
    if (root != null) {
      rootType = root.getClass().getCanonicalName();
      rootMessage = root.getMessage();
    }
  }

  /** Constructor for {@link BusinessException}.
   *
   * @param exception the {@link BusinessException} instance, cannot be null.
   */
  ErrorResponse(final BusinessException exception) {
    this((Exception) exception);
    entity = exception.getInstance();
    process = exception.getMethod();
  }
}
