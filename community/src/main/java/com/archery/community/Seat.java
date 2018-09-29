package com.archery.community;

import org.apache.commons.lang3.Validate;

/** The {@link Seat} is the place where the Tournament takes place and
 * it usually belongs to an Organization.
 */
public class Seat {
  private String name;

  private String address;

  private Type type;

  public Seat(final String theName, final String theAddress,
      final Type seatType) {
    Validate.notEmpty(theName, "The name cannot be empty");
    Validate.notEmpty(theAddress, "The address cannot be empty");

    name = theName;
    address = theAddress;
    type = seatType;
  }
}

/** Refactor to sub-classes as soon it is needed.
 */
enum Type {
  /** An outdoor seat. */
  FIELD,
  /** An indoor seat. */
  HALL
}
