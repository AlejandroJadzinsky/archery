package com.archery.infranstructure;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import java.time.OffsetDateTime;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.MultilineRecursiveToStringStyle;
import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/** Utility to return a String representation of an Object.
 */
public class ObjectLogger {
  /** Keeps the classes configured to use toString() method. */
  private static final Set<Class<?>> TO_STRING_CLASSES = new HashSet<>();

  static {
    TO_STRING_CLASSES.add(CharSequence.class);
    TO_STRING_CLASSES.add(Character.class);
    TO_STRING_CLASSES.add(Number.class);
    TO_STRING_CLASSES.add(Boolean.class);
    TO_STRING_CLASSES.add(OffsetDateTime.class);
  }

  /** Add a Class that will not be stringed using reflection but the default
   *  toString() method.
   *
   * @param clazz a Class, cannot be null.
   */
  public static void addToStringClass(final Class<?> clazz) {
    Validate.notNull(clazz, "The class cannot be null");

    TO_STRING_CLASSES.add(clazz);
  }

  /** Recursively traverse the Object to provide an in depth String
   *  representation in one line format.
   *
   * @param object the Object to stringify, can be null
   * @return a String representation of the Object.
   */
  public static String inDepthString(final Object object) {
    return toString(object, new InDepthStyle());
  }

  /** Recursively traverse the Object to provide an in depth String
   *  representation in multi line format.
   *
   * @param object the Object to stringify, can be null
   * @return a String representation of the Object.
   */
  public static String inDepthPretty(final Object object) {
    return toString(object, new InDepthPrettyStyle());
  }

  /** A String representation of the Object properties in one line format.
   *
   * @param object the Object to stringify, can be null
   * @return a String representation of the Object.
   */
  public static String shallowString(final Object object) {
    return toString(object, ToStringStyle.DEFAULT_STYLE);
  }

  /** A String representation of the Object properties in a multi line format.
   *
   * @param object the Object to stringify, can be null
   * @return a String representation of the Object.
   */
  public static String shallowPretty(final Object object) {
    return toString(object, ToStringStyle.MULTI_LINE_STYLE);
  }

  /** Entry point the the toString operation.
   *
   * @param object an Object instance, cannot be null.
   * @param style the selected ToStringStyle, cannot be null.
   *
   * @return a not empty String instance, never null.
   */
  private static String toString(final Object object,
      final ToStringStyle style) {
    if (object instanceof Collection<?>) {
      return toString((Collection<?>) object, style);
    }
    //TODO (A.J. 2017-04-01) add support for Map and Arrays

    return object.getClass().getSimpleName() + " = "
        + ToStringBuilder.reflectionToString(object, style, false, null);
  }

  /** Executes the toString delegation for Collections abstractions.
   *
   * @param collection a collection instance, cannot be null.
   * @param style the selected ToStringStyle, cannot be null.
   *
   * @return a not empty String instance, never null.
   */
  private static String toString(final Collection<?> collection,
      final ToStringStyle style) {
    return collection.stream()
        .map(o -> ToStringBuilder.reflectionToString(o, style, false, null))
        .collect(Collectors.joining(",", "{", "}"));
  }

  /** RecursiveToStringStyle extension with configured toString classes.
   */
  private static final class InDepthStyle extends RecursiveToStringStyle {
    /** Default constructor. */
    InDepthStyle() {
      setUseClassName(false);
      setUseIdentityHashCode(false);
      setFieldNameValueSeparator(" = ");
      setFieldSeparator(", ");
    }

    @Override
    public void appendDetail(final StringBuffer buffer, final String fieldName,
        final Object value) {
      Object local =  value;
      if (isToStringObject(value)) {
        local = value.toString();
      }
      super.appendDetail(buffer, fieldName, local);
    }
  };

  /** MultilineRecursiveToStringStyle extension with configured toString
   *  classes.
   */
  private static final class InDepthPrettyStyle
      extends MultilineRecursiveToStringStyle {
    /** Default constructor. */
    InDepthPrettyStyle() {
      setUseClassName(false);
      setUseIdentityHashCode(false);
      setFieldNameValueSeparator(" = ");
      setFieldSeparator(", ");
    }

    @Override
    public void appendDetail(final StringBuffer buffer, final String fieldName,
        final Object value) {
      Object local = value;
      if (isToStringObject(value)) {
        local = value.toString();
      }
      super.appendDetail(buffer, fieldName, local);
    }
  };

  /** Checks if the given Object is configured to use toString method.
   *
   * @param value the Object instance to be evaluated, cannot be null.
   * @return true if the Object must be Stringed ued toString, otherwise
   * false.
   */
  private static boolean isToStringObject(final Object value) {
    for (Class<?> clazz : TO_STRING_CLASSES) {
      if (clazz.isInstance(value)) {
        return true;
      }
    }
    return false;
  }
}
