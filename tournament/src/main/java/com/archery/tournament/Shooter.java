package com.archery.tournament;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/** Represents an archer involved in a tournament as a competitor.
 */
class Shooter implements Comparable<Shooter> {
  /** A friendly name to be used in the app, never null nor empty. */
  private String name;

  /** Creates a new {@link Shooter} instance.
   *
   * @param theName the shooter's name, cannot be null nor empty.
   */
  Shooter(final String theName) {
    Validate.notEmpty(theName, "The name is null or empty.");

    name = theName;
  }

  /** Information to identify this instance in logs or exception messages.
   *
   * @return a String, never null nor empty.
   */
  String logInfo() {
    return name;
  }

  @Override
  public int compareTo(final Shooter o) {
    return name.compareTo(o.name);
  }

  @Override
  public boolean equals(final Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj, name);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, name);
  }
}
