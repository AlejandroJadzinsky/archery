package com.archery.community;

import org.apache.commons.lang3.Validate;

/** The {@link Seat} is the place where the Tournament takes place and
 * it usually belongs to an Organization.
 */
public class Seat {
  /** The {@link Seat}'s name. */
  private String name;
  /** The {@link Seat}'s address. */
  private String address;
  /** The {@link Seat}'s {@link Type}. */
  private Type type;

  /** Creates a new {@link Seat} with mandatory parameters.
   *
   * @param theName the name, cannot be null.
   * @param theAddress the address, cannot be null.
   * @param seatType the type, cannot be null.
   */
  public Seat(final String theName, final String theAddress,
      final Type seatType) {
    Validate.notEmpty(theName, "The name cannot be empty");
    Validate.notEmpty(theAddress, "The address cannot be empty");
    Validate.notNull(seatType, "The type cannot be null");

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
