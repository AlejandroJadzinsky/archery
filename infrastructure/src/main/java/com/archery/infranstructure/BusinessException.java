package com.archery.infranstructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;

/** An Exception for business errors with specific information of the process,
 * entity or usage case involved.
 * It should be used in contrast to an "Unexpected Exception", when we know what
 * happened but the problem is usually a bad usage and can be resolved by the
 * client. The info in this exception must help the client fix the problem.
 */
public class BusinessException extends RuntimeException {
  @JsonProperty
  private String instance;
  @JsonProperty
  private String method;

  /** Creates a new {@link BusinessException} instance.
   *
   * @param message the exception message, cannot be null.
   * @param cause the original Exception, can be null when we are creating an
   * Exception from scratch.
   * @param process the method or process where the exception is generated.
   */
  public BusinessException(final String message, final Throwable cause,
      final String process) {
    super(message, cause);

    Validate.notEmpty(process, "process is null");
    method = process;
  }

  /** Creates a new {@link BusinessException} instance.
   *
   * @param message the exception message, cannot be null.
   * @param process the method or process where the exception is generated.
   */
  public BusinessException(final String message, final String process) {
    this(message, null, process);
  }

  /** Creates a new {@link BusinessException} instance.
   *
   * @param message the exception message, cannot be null.
   * @param cause the original Exception, can be null when we are creating an
   * Exception from scratch.
   * @param object the entity aspciated with the exception, cannot be null
   * @param process the method or process where the exception is generated.
   */
  public BusinessException(final String message, final Throwable cause,
      final Object object, final String process) {
    this(message, cause, process);
    Validate.notNull(object, "Object is null");

    instance = ObjectLogger.inDepthString(object);
  }

  /** Return the serialized representation of the entity that generates the
   * exception.
   *
   * @return the serialized object, can be null when no entity involved.
   */
  public String getInstance() {
    return instance;
  }

  /** Returns the method name that cause the exception
   *
   * @return the method name, never null nor empty.
   */
  public String getMethod() {
    return method;
  }
}
