package com.archery.tournament;

import org.apache.commons.lang3.Validate;

/** Represents the target or group of targets sheared by an Archer's Patrol.
 *
 * In an indoor (and some outdoor) contest, each Patrol use the same
 * {@link ShootingPosition} for the whole Round, while in field contests the
 * {@link ShootingPosition} is just the starting point of the Round trip.
 *
 */
class ShootingPosition {
  private int position;

  /** Creates a nre {@link ShootingPosition} instance.
   *
   * @param thePosition the position order, always greater than zero.
   */
  ShootingPosition(final int thePosition) {
    Validate.isTrue(thePosition > 0, "ShootinPosition order must be positive");

    position = thePosition;
  }

  /** Information to identify this instance in logs or exception messages.
   *
   * @return a String, never null nor empty.
   */
  String logInfo() {
    return Integer.toString(position);
  }

  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof ShootingPosition)) {
      return false;
    }

    return position == ((ShootingPosition) other).position;
  }

  @Override
  public int hashCode() {
    return position;
  }
}
